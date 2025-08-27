package View;

import java.util.Random;
import java.util.Scanner;

public class GameView {
	Scanner sc = new Scanner(System.in);
	Random rd = new Random();

	public void showBoard(String[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].equals("x")) {
					System.out.print("    ");
				} else if (board[i][j].equals("a")) {
					if (j == 1) {
						System.out.print("  Ａ");
					} else {
						System.out.print("Ａ  ");
					}
				} else if (board[i][j].equals("b")) {
					if (j == 1) {
						System.out.print("  Ｂ");
					} else {
						System.out.print("Ｂ  ");

					}
				} else if (board[i][j].equals("ab")) {
					System.out.print("ＢＡ");
				} else if (board[i][j].equals("ba")) {
					System.out.print("ＡＢ");
				} else {
					System.out.print(board[i][j]);
				}

			}
			if (i > 2 && i < 10) {
				if (i == 5) {
					System.out.println("          ┌───── Game Rule -─────┐");
					System.out.println(
							"            │                          │                  │      ♠ : break a turn         │");
				} else if (i == 6) {
					System.out.println("          │      ♥ : Switch A and B       │");
					System.out.println(
							"            │                          │                  │      ♣ : Random move          │");
				} else if (i == 7) {
					System.out.println("          └────────────────┘");
					System.out.println("            │                          │");
				} else {
					System.out.println();
					System.out.println("            │                          │");
				}

			} else if (i == 1) {
				System.out.println();
			} else if (i == 0 || i == board.length - 3 || i == board.length - 2) {
				System.out.println();
			} else {
				System.out.println();
				System.out.println();
			}

		}

	}

	public int showStartMenu() {
		System.out.println(" __    __   _______  __       __        ______    ");
		System.out.println("|  |  |  | |   ____||  |     |  |      /  __  ＼   ");
		System.out.println("|  |__|  | |  |__   |  |     |  |     |  |  |  |   ");
		System.out.println("|   __   | |   __|  |  |     |  |     |  |  |  |   ");
		System.out.println("|  |  |  | |  |____ |  `----.|  `----.|  `--'  |   ");
		System.out.println("|__|  |__| |_______||_______||_______| ＼______/   ");
		System.out.println("                                                                             ");
		System.out.println("                         R E T R O   G A M E   M O D E                       ");
		System.out.println();
		System.out.println("[1] 게임설명보기  [2] 바로 게임시작하기 ");
		
		int input = sc.nextInt();
		
		return input;
	}

	public void showDemo() {
		System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════════════╗");   
		System.out.println("║                                                                                                    　║"); 
		System.out.println("║                                        ※ 게임 규칙 안내 ※                                          ║");   
		System.out.println("║                                                                                                      ║"); 
		System.out.println("║   1. 팀 구성 및 승리조건 ☆☆                                                                        ║");                                         
		System.out.println("║    ★ 게임은 두 팀으로 진행됩니다.                                                                   ║");   
		System.out.println("║    ★ 각 팀은 목표 점수(혹은 목표 바퀴 수)에 먼저 도달하면 승리합니다.                               ║");   
		System.out.println("║    ★ 승리한 팀의 모든 팀원은 각 10점의 랭킹 포인트를 획득합니다.                                    ║");  
		System.out.println("║                                                                                                      ║");  
		System.out.println("║   2. 주사위와 문제 진행 ☆☆                                                                         ║");  
		System.out.println("║    ★ 플레이어는 주사위를 굴린 후, 다음 4종류의 문제 중 랜덤으로 한 문제를 받습니다.                 ║");  
		System.out.println("║       ① 홀/짝 맞추기   ② 업다운 게임   ③ 수도 퀴즈   ④ 넌센스 퀴즈                               ║");	                                                                                                                                                                                                   
		System.out.println("║    ★ 문제를 맞추면, 주사위에서 나온 수만큼 말을 이동할 수 있습니다.                                 ║");  
		System.out.println("║    ★ 제한 시간 초과 시 이동 불가                                                                    ║");  
		System.out.println("║    ★ 문제 난이도에 따라 제한 시간이 다르게 적용됩니다.                                              ║");  
		System.out.println("║    ★ 문제 입력 시도는 최대 3회로 제한하며, 3회 모두 실패하면 정답 실패로 간주합니다.                ║");  
		System.out.println("║                                                                                                      ║");  
		System.out.println("║   3. 이벤트 발판 규칙 ☆☆                                                                           ║");  
		System.out.println("║    ?  발판 : 랜덤으로 말을 이동하거나, 일정 범위 내 무작위 이동                                      ║");  
		System.out.println("║    ♥ 발판 : 상대 팀과 말을 서로 교체                                                                ║");  
		System.out.println("║    ♠ 발판 : 해당 팀은 한 턴 쉽니다.                                                                 ║");  
		System.out.println("║    ♣ 발판 : 무작위 위치로 이동 (이동 후 라운드는 증가하지 않음)                                     ║");  
		System.out.println("║    ※ 주의 : 랜덤 발판 이동 후 이벤트 발판은 한 번만 발생하며, 연속 발생하지 않습니다.               ║");  
		System.out.println("║              말을 바꾸는 이벤트 발생 시, 이동한 위치의 이벤트는 추가로 작동하지 않습니다.            ║");  
		System.out.println("║                                                                                                      ║");  
		System.out.println("║   4. 랭킹 시스템 ☆☆                                                                                ║");  
		System.out.println("║    ★ 게임 종료 후 승리한 팀의 팀원들은 추가 10점을 획득합니다.                                      ║");  
		System.out.println("║    ★ 랭킹 포인트에 따라 순위가 결정됩니다.                                                          ║");  
		System.out.println("║    ★ 승리를 통해 자신의 순위를 높여보세요!!! Fighting                                               ║");  
		System.out.println("║                                                                                                      ║");  
		System.out.println("║                                                                                                      ║");  
		System.out.println("║                                                                                                      ║");  
		System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════════╝");  
		
	}
	
	public int showInputGoal() {
		System.out.println("┌───────────────-──────────┐");  
		System.out.println("│                                                   │");  
		System.out.println("│ 목표 완주횟수(최대5)를 입력하면 게임이 시작됩니다 │");  
		System.out.println("│                                                   │");  
		System.out.println("└─────────────────────────-┘"); 
		System.out.print("     목표 완주 횟수 입력 >>      ");
		int input = sc.nextInt();
		System.out.println("   ");
		return input;
	}

	public String showFirstTeam() {
		String target = "";
		if (rd.nextInt(100) + 1 > 50) {
			target = "A";
		} else {
			target = "B";
		}
		 	System.out.println("╔════════════════════════════════╗");
	        System.out.println("║   ⚔ 선공 팀을 결정 ing... ⚔　║");
	        System.out.println("║   어느 팀이 먼저 움직일까요?!　║");
	        System.out.println("╚════════════════════════════════╝");
	        System.out.println();

	        try {
	            System.out.println("⌛ 랜덤 시뮬레이션 시작...");
	            Thread.sleep(1000);

	            System.out.println("⌛ 선공팀 뽑는 중...");
	            Thread.sleep(1000);

	            System.out.println("♬ 운명 결정 완료!");
	            Thread.sleep(500);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        // 선공팀 무작위 결정 (A팀 또는 B팀)
	        System.out.println("\n✨✨ 선공 팀 결정 완료!");
	        System.out.println(" 선공 팀은... [ " + target + "팀 당첨 ]");
	        System.out.print("⏳");

	        for (int i = 0; i < 5; i++) {
	            try {
	                Thread.sleep(300);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.print(" .");
	        }

	        System.out.println("게임이 시작됩니다! 행운을 빕니다! 🎉");
	        System.out.println();
	        System.out.println();
		return target;
	}

	public int showDice(String target) {
		
		System.out.println("     ┌───────── " + target + " Team turn ──────────┐");
		System.out.println("     │                                                   │");
		System.out.println("     │       주사위를 던지려면 숫자 '1'을 입력하세yo     │");
		System.out.println("     │                                                   │");
		System.out.println("     └─────────────────────────-┘");
		int throwDice = sc.nextInt();
		
		
		return throwDice;
	}

	public int showDiceResult(boolean chkDice) {
			this.loading();
		        // 주사위 2개 굴리기 (1~6)
		        int dice1 = rd.nextInt(6) + 1;
		        int dice2 = rd.nextInt(6) + 1;

		        // 아스키 아트 주사위
		        String[][] diceFaces = {
		            { // 1
		              "+-------+",
		              "|       |",
		              "|   o   |",
		              "|       |",
		              "+-------+"
		            },
		            { // 2
		              "+-------+",
		              "| o     |",
		              "|       |",
		              "|     o |",
		              "+-------+"
		            },
		            { // 3
		              "+-------+",
		              "| o     |",
		              "|   o   |",
		              "|     o |",
		              "+-------+"
		            },
		            { // 4
		              "+-------+",
		              "| o   o |",
		              "|       |",
		              "| o   o |",
		              "+-------+"
		            },
		            { // 5
		              "+-------+",
		              "| o   o |",
		              "|   o   |",
		              "| o   o |",
		              "+-------+"
		            },
		            { // 6
		              "+-------+",
		              "| o   o |",
		              "| o   o |",
		              "| o   o |",
		              "+-------+"
		            }
		        };

		        // 주사위 두 개 옆으로 출력
		        String[] d1 = diceFaces[dice1 - 1];
		        String[] d2 = diceFaces[dice2 - 1];

		        for (int i = 0; i < d1.length; i++) {
		        	if(chkDice) {
		        		System.out.println(d1[i] + "   " + d2[i]);
		        	}else {
		        		System.out.println(d1[i]);
		        	}
		        }

		
		int sum = dice1 + dice2;
		if (chkDice) {
			String trim = sum > 9 ? "" : " ";
			System.out.println("     ❗ 주사위는 [" + dice1 + ", " + dice2 + "]로 합이'" + sum + "'이(가) 나왔습니다");
			System.out.println();
		} else {
			sum = dice1;
			System.out.println("     ❗ 주사위는 " + sum + "'이(가) 나왔습니다");
		}
		return sum;
	}

	public void showSuccess(String target, int sum) {
		System.out.println();
		System.out.println("  퀴즈풀이에 성공하여 <" + target + "팀>의 말을 " + sum + "만큼 움직입니다");
	}

	public void wrongInputMessage() {
		System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
	}
	
	public void loadingMessage() {
		System.out.println();
		System.out.println("게임 불러오는중...");
		try {
			for(int i=3; i>0; i--) {
				Thread.sleep(1000);
				System.out.print(i + "...");
				
			}
			System.out.println("시작!!!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	public void loading() {
		System.out.println();
		try {
			for(int i=0; i<3; i++) {
				Thread.sleep(400);
				System.out.print(".");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	public void showBreakTurn(String target) {
		 	System.out.println("╔══════════════════════════════════════╗");
	        System.out.println("║      ♥ 무인도에 도착했습니다!♥     ║");
	        System.out.println("╚══════════════════════════════════════╝");
	        System.out.println();
	        
	        try {
	            System.out.println("> 당신은 무인도에 갇혔습니다...");
	            Thread.sleep(800);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        try {
	            System.out.println("> 구조 신호를 보내는 중...");
	            Thread.sleep(1200);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        try {
	            System.out.println("> 그러나 아무도 오지 않았습니다.");
	            Thread.sleep(400);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        System.out.println();
	        System.out.println("────────────────────────────────────");
	        System.out.println("                    ㅠ 아쉽지만 한 턴 쉽니다 ㅠ");
	        System.out.println("────────────────────────────────────");
	}

	public void showEscapeTurn(String target) {
		System.out.println(target.equals("A") ? "B" : "A" + "팀이 무인도에서 빠져나왔습니다!");
	}

	public void showSwitch() {
		System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     ※ CHANGE 칸에 도착했습니다! ※  ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();
        System.out.print("> CHANGE 시스템 작동 중...");
        for(int i=0; i<3; i++) {
        	try {
        		Thread.sleep(400);
            	if(i==2) {
            		System.out.println(".");
            	}else {
            		System.out.print(".");
            	}
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        System.out.println("> 상대와 위치를 바꿀 기회!");
        try {
        	Thread.sleep(400);
		} catch (Exception e) {
			e.printStackTrace();
		}

        System.out.println();
        System.out.println("────────────────────────────────────");
        System.out.println("               ★  운명을 뒤집어라! 위치 교환! ★ ");
        System.out.println("────────────────────────────────────");
	}

	public int showRandomGame() {
		System.out.println("랜덤 이벤트 발생~!");

		int random = rd.nextInt(3);
		for (int i = 0; i < 4; i++) {

			try {
				System.out.print("하");
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println(" 뭐가 나올까유~? ");
		return random;
	}

	public void showRandomDice(String target, int rdNum, String emote) {
		System.out.println("<" + target + "팀>의 말을 " + rdNum + "만큼 움직입니다" + emote);
	}

	public void showOneMore() {
		System.out.println("주사위 한 번 더~ 찬스!(단, 문제는 풀어야 말 움직임)");
	}

	public void showOneDice(String target) {
		System.out.println("다음턴엔 " + target + "팀은 주사위 하나로 던집니다");

	}

	public void showWrongAnswer() {
		System.err.println("잘못 입력했습니다. Enter키를 누르세요");
	}

	public void showFaild() {
		System.out.println("미션 실패하여 말을 움직이지 못했습니다.");
		
	}

	public void showWinnerTeam(String target) {
		System.out.println(target + "팀이 승리하였습니다~!!!!!");
	}

}
