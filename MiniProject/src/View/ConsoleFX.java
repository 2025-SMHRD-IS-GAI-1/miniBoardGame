package View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;


public final class ConsoleFX {

	private ConsoleFX() {
	}

	// ====== 공통 유틸 ======
	public static void clear() {
	    boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

	    if (!isWindows) {
	        // Windows가 아니면 ANSI로 빠르게 클리어
	        System.out.print("\u001b[H\u001b[2J");
	        System.out.flush();
	    }

	    // CMD 안전: 줄바꿈으로 화면 밀어내기
	    for (int i = 0; i < 40; i++) System.out.println();
	}

	private static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ignored) {
		}
	}

	private static void printFrame(String frame) {
		System.out.print(frame);
		System.out.flush();
	}

	private static String center(String s, int width) {
		if (s.length() >= width)
			return s;
		int pad = (width - s.length()) / 2;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pad; i++)
			sb.append(' ');
		sb.append(s);
		return sb.toString();
	}

	public static void frogJumpToGoalAscii() {
	    frogJumpToGoalAscii(60, 24, 3); // goal 위치, 프레임 지연(ms), 점프 횟수
	}

	public static void frogJumpToGoalAscii(int goalPos, int delayMs, int hops) {
	    // 개구리 프레임(역슬래시 X) — 다리/자세 바꾸며 점프 느낌
	    String[][] frog = new String[][]{
	        { // 준비 자세
	            "  oo  ",
	            " (  ) ",
	            " (^^) ",
	            "  //  "
	        },
	        { // 도약
	            "  oo  ",
	            " (  ) ",
	            " (^^) ",
	            "  ||  "
	        },
	        { // 착지
	            "  oo  ",
	            " (  ) ",
	            " (^^) ",
	            "  --  "
	        }
	    };
	    // 골인 라인(높이 5줄)
	    String[] goal = new String[]{
	        "  ||  ",
	        "  ||  ",
	        " GOAL ",
	        "  ||  ",
	        "  ||  "
	    };

	    final int FH = frog[0].length;       // 개구리 높이
	    final int FW = frog[0][0].length();  // 개구리 너비
	    final int GH = goal.length;          // 골인 높이
	    final int baseTop = 3;               // 개구리 기본 상단 위치(캔버스 위쪽 여백)
	    final int amp = 3;                   // 점프 높이(줄 수)

	    goalPos = Math.max(FW + 8, goalPos);               // 너무 가까우면 보정
	    int totalSteps = goalPos - FW;                      // 전체 이동 칸수
	    hops = Math.max(1, hops);
	    int hopLen = Math.max(6, totalSteps / hops);        // 점프 1회당 이동 칸수

	    for (int step = 0; step <= totalSteps; step++) {
	        clear();

	        // 현재 점프 구간 내 진행도(0~1)
	        double p = (step % hopLen) / (double)hopLen;
	        // 포물선 형태 높이: y = -4(p-0.5)^2 + 1  (0~1 -> 0~1~0)
	        double parabola = 1.0 - 4.0 * (p - 0.5) * (p - 0.5);
	        int y = (int)Math.round(Math.max(0, parabola) * amp);   // 올라가는 높이(줄 수)

	        // 행 단위로 프레임 합성 (개구리+골대)
	        StringBuilder frame = new StringBuilder();
	        int topFrog = baseTop + (amp - y);     // 높을수록 위로 당김
	        int topGoal = baseTop + amp - 1;       // 골대는 거의 고정

	        int canvasH = Math.max(topFrog + FH, topGoal + GH) + 1;

	        for (int row = 0; row < canvasH; row++) {
	            StringBuilder line = new StringBuilder();

	            // 개구리 출력 범위라면 그리기
	            if (row >= topFrog && row < topFrog + FH) {
	                String frogLine = frog[(step / 2) % frog.length][row - topFrog];
	                line.append(spaces(step))       // 가로 위치
	                    .append(frogLine);
	                // 골대까지의 빈칸
	                int gap = goalPos - (step + FW);
	                if (gap > 0) line.append(spaces(gap));
	            } else {
	                // 개구리가 없는 줄이면 골대 위치까지 공백
	                line.append(spaces(goalPos));
	            }

	            // 골대 출력
	            if (row >= topGoal && row < topGoal + GH) {
	                line.append(goal[row - topGoal]);
	            }
	            frame.append(line).append('\n');
	        }

	        // 하단 텍스트(살짝 움직임)
	        frame.append('\n')
	             .append(spaces(Math.max(0, step)))
	             .append("JUMP!  JUMP!\n");

	        printFrame(frame.toString());
	        sleep(Math.max(16, delayMs));
	    }

	    // 골인 컷 + 컨페티(있으면)
//	    clear();
//	    printFrame("\n" + center(" ( ^_^)  GOAL!! ", goalPos + 8) + "\n");
//	    try { confettiCheer(10); } catch (Throwable ignore) {}
	}

	// --- helper: 공백 n칸 (클래스에 없으면 같이 추가)
	private static String spaces(int n) {
	    if (n <= 0) return "";
	    StringBuilder sb = new StringBuilder(n);
	    for (int i = 0; i < n; i++) sb.append(' ');
	    return sb.toString();
	}

	// ====== 3) 비 맞는 이모(ASCII) (Fail용, 귀엽게 풉..) ======
	public static void sadRain(int frames) {
		final int W = 36;
		final int H = 8;
		Random r = new Random();
		for (int f = 0; f < frames; f++) {
			clear();
			StringBuilder sb = new StringBuilder();
			for (int y = 0; y < H; y++) {
				for (int x = 0; x < W; x++) {
					double p = 0.03 + (double) f / (frames * 25.0);
					sb.append(r.nextDouble() < p ? (r.nextBoolean() ? '.' : '|') : ' ');
				}
				sb.append('\n');
			}
			sb.append('\n');
			sb.append(center("  ( ; _ ; )  ", W)).append('\n');
			sb.append(center("   TRY AGAIN  ", W)).append('\n');
			printFrame(sb.toString());
			sleep(90);
		}
		clear();
		printFrame(center("  (._.) ... 다음엔 된다  ", 36) + "\n");
		sleep(300);
	}

	// ====== 4) 반짝 텍스트 (어떤 문구든 귀엽게 강조) ======
	public static void sparkleText(String text, int times) {
		String a = " * " + text + " * ";
		String b = "* * " + text + " * *";
		for (int i = 0; i < times; i++) {
			clear();
			printFrame("\n" + center(a, Math.max(a.length(), b.length()) + 6) + "\n");
			sleep(120);
			clear();
			printFrame("\n" + center(b, Math.max(a.length(), b.length()) + 6) + "\n");
			sleep(120);
		}
	}

	// ====== (옵션) 제한시간 입력 유틸: limit초 안에 입력 없으면 null 반환 ======
	public static String timedReadLine(String prompt, int limitSeconds) {
		if (prompt != null && !prompt.isEmpty()) {
			System.out.print(prompt);
			System.out.flush();
		}
		final String[] box = new String[1];
		Thread t = new Thread(() -> {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				box[0] = br.readLine(); // System.in 닫지 않음
			} catch (Exception ignored) {
			}
		});
		t.setDaemon(true); // 시간 지나도 메인 쓰레드 종료 방해 안 함
		t.start();
		try {
			t.join(Math.max(0, limitSeconds) * 1000L);
		} catch (InterruptedException ignored) {
		}
		return box[0]; // 시간 내 입력 없으면 null
	}
	// ===== 한 줄 좌↔우 살짝 흔들기 (wiggle) =====
	public static void wiggle(String msg) { wiggle(msg, 8, 60); }

	public static void wiggle(String msg, int cycles, int delayMs) {
	    int[] offsets = {0, 1, 2, 1}; // 왕복 패턴
	    for (int i = 0; i < Math.max(1, cycles); i++) {
	        int pad = offsets[i % offsets.length];
	        System.out.print("\r" + spaces(pad) + msg);
	        System.out.flush();
	        try { Thread.sleep(Math.max(16, delayMs)); } catch (InterruptedException ignored) {}
	    }
	    // 최종 고정 + 줄바꿈
	    System.out.print("\r" + msg + "   \n");
	}

	// ===== 스피너(로딩 느낌) =====
	public static void spinner(String msg) { spinner(msg, 10, 60); }

	public static void spinner(String msg, int cycles, int delayMs) {
	    char[] spin = {'|','/','-','\\'};
	    for (int i = 0; i < Math.max(1, cycles); i++) {
	        System.out.print("\r" + msg + "  [" + spin[i % spin.length] + "]");
	        System.out.flush();
	        try { Thread.sleep(Math.max(16, delayMs)); } catch (InterruptedException ignored) {}
	    }
	    System.out.print("\r" + msg + "      \n");
	}

	// 공백 n칸 (이미 있으면 중복 추가하지 마)
	private static String spaces1(int n) {
	    if (n <= 0) return "";
	    StringBuilder sb = new StringBuilder(n);
	    for (int i = 0; i < n; i++) sb.append(' ');
	    return sb.toString();
	}
	
	 // 개레전드 폭죽쇼 + 최종 문구: "(폭죽) ? 팀 승리 ! 축하드립니다! (폭죽)"
    public static void fireworksWinShow(String teamName) {
        int width = 84, height = 24, launches = 6, delayMs = 24;
        Random r = new Random();

        // 1) 로켓 다발 발사 + 개별 폭발
        for (int shot = 0; shot < launches; shot++) {
            int x = 6 + r.nextInt(width - 12);
            int peak = 3 + r.nextInt(height / 2);

            // 상승
            for (int y = height - 2; y >= peak; y--) {
                clear();
                char[][] cv = blankCanvas(width, height);
                starField(cv, 0.03, r);
                drawRocket(cv, x, y, r);
                groundLine(cv);
                printFrame(toString(cv));
                sleep(delayMs - 6);
            }

            // 폭발(원형 확산)
            int maxR = Math.min(10, Math.min(x - 2, width - x - 2));
            for (int radius = 1; radius <= maxR; radius++) {
                clear();
                char[][] cv = blankCanvas(width, height);
                starField(cv, 0.02, r);
                explodeRing(cv, x, peak, radius, r);
                groundLine(cv);
                printFrame(toString(cv));
                sleep(delayMs);
            }
        }

        // 2) 피날레: 화면 곳곳에서 동시 다발 폭발
        int[][] centers = new int[][]{
            {width/5, height/4}, {width*2/5, height/5}, {width*3/5, height/4},
            {width*4/5, height/5}, {width/2, height/3}
        };
        for (int frame = 1; frame <= 10; frame++) {
            clear();
            char[][] cv = blankCanvas(width, height);
            starField(cv, 0.02, r);
            for (int[] c : centers) explodeRing(cv, c[0], c[1], Math.min(frame, 9), r);
            groundLine(cv);
            printFrame(toString(cv));
            sleep(delayMs);
        }

        // 3) 마지막 한 컷: 중앙에 승리 문구를 "딱" (한 번만)
        clear();
        char[][] cv = blankCanvas(width, height);
        starField(cv, 0.02, r);
        // 배경 폭죽 고정
        for (int[] c : centers) explodeRing(cv, c[0], c[1], 9, r);

        String msg = "<*> " + teamName + " 팀 승리 ! 축하드립니다! <*>";
        overlayCentered(cv, height/2 - 1, msg);
        overlayCentered(cv, height/2 + 1, "====================================");

        groundLine(cv);
        printFrame(toString(cv));
        sleep(1200);
    }

    /* ===================== Internals ===================== */

    public static void clear1() {
        // CMD 안전: 줄바꿈으로만 밀어내기
        for (int i = 0; i < 40; i++) System.out.println();
    }
    private static void sleep1(int ms) { try { Thread.sleep(Math.max(10, ms)); } catch (InterruptedException ignored) {} }
    private static void printFrame1(String s) { System.out.print(s); System.out.flush(); }

    private static char[][] blankCanvas(int w, int h) {
        char[][] c = new char[h][w];
        for (int i = 0; i < h; i++) for (int j = 0; j < w; j++) c[i][j] = ' ';
        return c;
    }
    private static String toString(char[][] c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c.length; i++) sb.append(c[i]).append('\n');
        return sb.toString();
    }
    private static boolean in(char[][] c, int x, int y) {
        return y >= 0 && y < c.length && x >= 0 && x < c[0].length;
    }
    private static void put(char[][] c, int x, int y, char ch) {
        if (in(c, x, y)) c[y][x] = ch;
    }

    private static void starField(char[][] c, double dens, Random r) {
        for (int i = 0; i < c.length; i++)
            for (int j = 0; j < c[0].length; j++)
                if (r.nextDouble() < dens) c[i][j] = r.nextBoolean() ? '.' : '+';
    }
    private static void groundLine(char[][] c) {
        int y = c.length - 1;
        for (int x = 0; x < c[0].length; x++) c[y][x] = (x % 2 == 0) ? '_' : '=';
    }

    // 로켓 본체 + 꼬리 스파크 (역슬래시 없이)
    private static void drawRocket(char[][] c, int x, int y, Random r) {
        put(c, x, y,   '^');
        put(c, x, y+1, '|');
        put(c, x, y+2, '|');
        if (in(c, x,   y+3)) put(c, x,   y+3, '\'');
        if (r.nextBoolean() && in(c, x-1, y+2)) put(c, x-1, y+2, '.');
        if (r.nextBoolean() && in(c, x+1, y+2)) put(c, x+1, y+2, '.');
    }

    private static char ringChar(Random r) {
        int k = r.nextInt(3);
        return k == 0 ? '*' : (k == 1 ? 'o' : '+');
    }
    // 원형 링(24방향 근사)
    private static void explodeRing(char[][] c, int cx, int cy, int radius, Random r) {
        for (int k = 0; k < 24; k++) {
            double th = (Math.PI * 2 * k) / 24.0;
            int x = cx + (int)Math.round(radius * Math.cos(th));
            int y = cy + (int)Math.round(radius * Math.sin(th));
            if (in(c, x, y)) put(c, x, y, ringChar(r));
        }
        put(c, cx, cy, '*');
    }

    // 중앙 정렬 오버레이(한 줄)
    private static void overlayCentered(char[][] c, int row, String text) {
        if (row < 0 || row >= c.length) return;
        int w = c[0].length;
        int start = Math.max(0, (w - text.length()) / 2);
        for (int i = 0; i < text.length() && start + i < w; i++) {
            c[row][start + i] = text.charAt(i);
        }
    }
}
