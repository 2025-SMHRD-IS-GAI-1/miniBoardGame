package Controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import Model.NonsenseDAO;
import Model.NonsenseQuizVO;
import View.NonsenseQuizView;

public class NonsenseQuizController implements MiniGameController{

    private final NonsenseDAO dao = new NonsenseDAO();
    private final NonsenseQuizView view = new NonsenseQuizView(); // ★ View 사용

    public NonsenseQuizController(NonsenseQuizView nonsenseQuizView, NonsenseDAO nonsenseDAO) {
        // 필요 시 DI용 생성자 (지금은 미사용)
    }

    /**
     * 퀴즈 시작 (제한시간 + 최대 3회 시도)
     * - 핵심 변경점
     *   1) 입력을 별도 스레드(ExecutorService)로 받아 Future로 관리  // [타이머-핵심]
     *   2) Future.get(남은시간, TimeUnit.SECONDS)로 제한시간 강제       // [타이머-핵심]
     *   3) 시간 초과시 즉시 종료 처리                                     // [타임아웃 처리]
     */
    public boolean gameStart() {
        boolean result = false;

        view.showWelcome();

        NonsenseQuizVO quiz = dao.randomNonQuiz(); // 랜덤 문제 (DAO)
        if (quiz == null) {
            view.showNoQuestion();
            return false;
        }

        view.showQuiz(quiz);

        // ★ 제한시간(초) : DB에 저장된 값 사용
        final int totalSeconds = Math.max(1, quiz.getTime()); // 0 방지
        final long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(totalSeconds);

        // [타이머-핵심] 입력 전용 스레드 풀(단일)
        ExecutorService es = Executors.newSingleThreadExecutor();

        try {
            // 최대 3번 시도
            for (int chance = 3; chance >= 1; chance--) {

                // 남은 시간 계산
                long now = System.nanoTime();
                long remainNanos = deadline - now;
                long remainSec = TimeUnit.NANOSECONDS.toSeconds(Math.max(0, remainNanos));

                if (remainSec <= 0) {
                    // [타임아웃 처리]
                    safeShowTimeout();
                    view.showResult(false);
                    return false;
                }

                // 남은 기회 안내
                if (chance < 3) {
                    view.showRemainChance(chance); // 남은 횟수 출력(기존 메서드 활용)
                }

                // [타이머-핵심] 입력 작업 제출 (View.getUserInput()은 블로킹이므로 별도 스레드에서 수행)
                Future<String> fut = es.submit(() -> view.getUserInput());

                // --- 선택사항: 남은 시간 표시 이펙트 ---
                // 콘솔에서 매초 남은시간을 표시하고 싶다면, 아래의 "보조 타이머" 쓰레드를 켜고 끄면 됨.
                // View에 전용 메서드가 없으면 System.out.print로 대체 가능.
                boolean[] ticking = new boolean[]{true};
                Thread tickThread = new Thread(() -> {
                    long lastShown = -1;
                    while (ticking[0]) {
                        long nowNs = System.nanoTime();
                        long left = TimeUnit.NANOSECONDS.toSeconds(Math.max(0, deadline - nowNs));

                        // ★ 변경: 5초 단위로만 출력
                        if (left % 5 == 0 && left != lastShown) {
                            lastShown = left;
                            System.out.print("\r[남은 시간] " + left + "초 ");
                            System.out.flush();
                        }

                        try { Thread.sleep(1000); } catch (InterruptedException ignored) { }
                        if (left <= 0) break;
                    }
                });
                tickThread.setDaemon(true);
                tickThread.start();
                // --- 선택사항 끝 ---

                String input;
                try {
                    // [타이머-핵심] 남은 시간만큼만 입력 대기
                    input = fut.get(remainSec, TimeUnit.SECONDS);
                } catch (TimeoutException te) {
                    // 입력 스레드 취소
                    fut.cancel(true);
                    ticking[0] = false;
                    try { tickThread.join(300); } catch (InterruptedException ignored) { }
                    System.out.println(); // 줄바꿈 정리
                    // [타임아웃 처리]
                    safeShowTimeout();
                    view.showResult(false);
                    return false;
                } catch (ExecutionException ee) {
                    ticking[0] = false;
                    try { tickThread.join(300); } catch (InterruptedException ignored) { }
                    System.out.println();
                    ee.getCause().printStackTrace();
                    view.showResult(false);
                    return false;
                } catch (InterruptedException ie) {
                    ticking[0] = false;
                    try { tickThread.join(300); } catch (InterruptedException ignored) { }
                    System.out.println();
                    Thread.currentThread().interrupt();
                    view.showResult(false);
                    return false;
                } finally {
                    // 보조 타이머 종료
                    ticking[0] = false;
                    try { tickThread.join(300); } catch (InterruptedException ignored) { }
                    System.out.println(); // 줄바꿈 정리
                }

                // 정답 체크
                result = dao.checkAnswer(quiz, input);
                if (result) {
                    view.showResult(true);
                    return true;
                } else {
                    if (chance == 1) { // 마지막 오답
                        view.showResult(false);
                        return false;
                    }
                    // 다음 루프에서 남은 기회 안내 후 재시도
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.shutdownNow(); // ★ 스레드풀 정리
        }

        return result;
    }

    // [타임아웃 처리] View에 전용 이펙트가 있다면 여기서 호출하도록 안전하게 래핑
    private void safeShowTimeout() {
        try {
            // 예: view.showTimeoutEffect(); 가 있다면 사용
            // 없다면 기본 메시지 출력
            System.out.println("⏰ 시간 초과! 다음 기회에…");
        } catch (Throwable ignored) {
            System.out.println("⏰ 시간 초과! 다음 기회에…");
        }
    }
}