package Model;
import java.util.Scanner;
import java.util.concurrent.*;

public class QuizWithTimeout {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // 시간 제한 설정 (예: 5초)
        int timeoutSeconds = 5;

        // 제한시간이 지나면 실행될 작업
        Future<?> timeoutTask = executor.schedule(() -> {
            System.out.println("\n⏰ 시간 초과!");
            System.exit(0); // 프로그램 종료 or 다음 문제로 넘어가기 로직
        }, timeoutSeconds, TimeUnit.SECONDS);

        System.out.println("문제: 1 + 1 = ?");
        System.out.print("답을 입력하세요 (" + timeoutSeconds + "초 내): ");

        String answer = scanner.nextLine();
        timeoutTask.cancel(true); // 유저가 입력하면 타이머 취소

        if ("2".equals(answer.trim())) {
            System.out.println("✅ 정답입니다!");
        } else {
            System.out.println("❌ 오답입니다!");
        }

        executor.shutdown();
    }
}
