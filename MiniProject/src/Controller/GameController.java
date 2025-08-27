package Controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Model.Game;
import Model.MemberVO;
import Model.MiniGameService;
import Model.TeamService;
import View.GameView;

public class GameController {
	Scanner sc = new Scanner(System.in);
	Random rd = new Random();

	Game game;
	GameView view;
	TeamService teamService;

	public GameController(GameView view, Game game, TeamService teamService) {
		this.view = view;
		this.game = game;
		this.teamService = teamService;
	}

	public ArrayList<MemberVO> gameStart() {
		boolean chkStart = false;
		while(true) {
			int input = view.showStartMenu();
			if(input == 1) {
				
				while(true) {
					view.showDemo();
					int goal = view.showInputGoal();
					if(goal >= 1 && goal <= 5) {
						game.setGoal(goal);
						chkStart = true;
						break;
					}else {
						view.wrongInputMessage();
					}
				}
				if(chkStart) {
					break;
				}
			}else if(input == 2) {
				while(true) {
				int goal = view.showInputGoal();
				if(goal >= 1 && goal <= 5) {
					game.setGoal(goal);
					chkStart = true;
					break;
				}else {
					view.wrongInputMessage();
				}
				if(chkStart) {
					break;
				}
				}
			
			}else {
				view.wrongInputMessage();
			}
		}
		boolean chkBreakTurn = false;
		boolean chkChange = true;
		int chkCount = 0;
		boolean chkDice = true;
		String chkTarget = "";
		game.locatePath();
		game.setTarget(view.showFirstTeam());
		view.showBoard(game.getBoard());
		while (true) {
			
			if (view.showDice(game.getTarget()) == 1) {
				boolean mission = false;
				if (chkTarget.equals(game.getTarget())) {
					game.setSum(view.showDiceResult(chkDice));
					chkDice = true;
				} else {
					game.setSum(view.showDiceResult(true));
				}

				// 문제 출제 //
//				while (sc.hasNextLine()) {
//					String clear = sc.nextLine();
//					if (clear.trim().isEmpty())
//						break;
//				}
				view.loadingMessage();
				MiniGameController selectedGame = new MiniGameService().pickRandomMiniGame();
				mission = selectedGame.gameStart();
//				System.out.print("      정답 입력 >>  "); // 퀴즈 뷰
//				String answer = sc.next();
//				if (answer.equals("1")) {
//					mission = true;
//				}

				if (mission) {
					view.showSuccess(game.getTarget(), game.getSum());
					if (game.getTarget().equals("A")) {
						game.deleteA();
						game.moveA();
					} else {
						game.deleteB();
						game.moveB();
					}
					chkChange = true;
					if (chkBreakTurn) { // 무인도 활성화일때
						chkChange = false; // 턴체인지 비활성화
						chkCount++;
					}

					view.showBoard(game.getBoard());
					if(game.getSumA() == 27 || game.getSumB() == 27) {
						if (game.getTarget().equals("A")) {
							System.out.println("A팀 말을 랜덤으로 배정합니다");
							int random = 0;
							while(true) {
								random = rd.nextInt(34)+1;
								
								if(random != 27) {
									break;
								}
							}
							game.deleteA();
							game.setSum(0);
							game.setSumA(random);
							game.moveA();
							view.showBoard(game.getBoard());							
						}else {
							System.out.println("B팀 말을 랜덤으로 배정합니다");
							int random = 0;
							while(true) {
								random = rd.nextInt(34)+1;
								
								if(random != 27) {
									break;
								}
							}
							game.deleteA();
							game.setSum(0);
							game.setSumA(random);
							game.moveA();
							view.showBoard(game.getBoard());
						}
						if (game.getSumA() == 9 || game.getSumB() == 9) {
							if (chkChange && !chkBreakTurn && chkCount == 0) { // 무인도 비활성화 및 턴체인지 활성화일때
								view.showBreakTurn(game.getTarget());
								chkBreakTurn = true;// 무인도 활성화
							} else if (!chkChange && chkBreakTurn && chkCount == 2) {
								chkBreakTurn = false;
								chkChange = true;
								view.showEscapeTurn(game.getTarget());
								chkCount = 0;
							}

						}
						if (game.getSumA() == 18 || game.getSumB() == 18) {
							view.showSwitch();
							game.switchBoth();
							view.showBoard(game.getBoard());
						}

						if (game.getTarget().equals("A")) {
							while (game.getSumA() == 4 || game.getSumA() == 14 || game.getSumA() == 22
									|| game.getSumA() == 32) {
								int random = view.showRandomGame();
								int[] randomValues = { -4, -3, -2, -1, 1, 2, 3, 4 };

								int rdNum = randomValues[rd.nextInt(randomValues.length)];
								String emote = rdNum > 0 ? "!!!!! 나이사 " : "ㅠㅠ";
								if (random == 0) {

									view.showRandomDice(game.getTarget(), rdNum, emote);
									game.deleteA();
									game.setSum(rdNum);
									game.moveA();
									view.showBoard(game.getBoard());
								} else if (random == 1) {
									view.showOneMore();
									chkChange = false;
									break;
								} else if (random == 2) {
									view.showOneDice(game.getTarget());
									chkDice = false;
									chkTarget = game.getTarget();
									break;
								}
							}
						} else {
							while (game.getSumB() == 4 || game.getSumB() == 14 || game.getSumB() == 22
									|| game.getSumB() == 32) {
								int random = view.showRandomGame();
								int[] randomValues = { -4, -3, -2, -1, 1, 2, 3, 4 };

								int rdNum = randomValues[rd.nextInt(randomValues.length)];
								String emote = rdNum > 0 ? "!!!!! 나이사 " : "ㅠㅠ";
								if (random == 0) {
									view.showRandomDice(game.getTarget(), rdNum, emote);
									game.deleteB();
									game.setSum(rdNum);
									game.moveB();
									view.showBoard(game.getBoard());
								} else if (random == 1) {
									view.showOneMore();
									chkChange = false;
									break;
								} else if (random == 2) {
									view.showOneDice(game.getTarget());
									chkDice = false;
									chkTarget = game.getTarget();
									break;
								}
							}

						}
					}else {
						if (game.getSumA() == 9 || game.getSumB() == 9) {
							if (chkChange && !chkBreakTurn && chkCount == 0) { // 무인도 비활성화 및 턴체인지 활성화일때
								view.showBreakTurn(game.getTarget());
								chkBreakTurn = true;// 무인도 활성화
							} else if (!chkChange && chkBreakTurn && chkCount == 2) {
								chkBreakTurn = false;
								chkChange = true;
								view.showEscapeTurn(game.getTarget());
								chkCount = 0;
							}

						}
						if (game.getSumA() == 18 || game.getSumB() == 18) {
							view.showSwitch();
							game.switchBoth();
							view.showBoard(game.getBoard());
						}

						if (game.getTarget().equals("A")) {
							while (game.getSumA() == 4 || game.getSumA() == 14 || game.getSumA() == 22
									|| game.getSumA() == 32) {
								int random = view.showRandomGame();
								int[] randomValues = { -4, -3, -2, -1, 1, 2, 3, 4 };

								int rdNum = randomValues[rd.nextInt(randomValues.length)];
								String emote = rdNum > 0 ? "!!!!! 나이사 " : "ㅠㅠ";
								if (random == 0) {

									view.showRandomDice(game.getTarget(), rdNum, emote);
									game.deleteA();
									game.setSum(rdNum);
									game.moveA();
									view.showBoard(game.getBoard());
								} else if (random == 1) {
									view.showOneMore();
									chkChange = false;
									break;
								} else if (random == 2) {
									view.showOneDice(game.getTarget());
									chkDice = false;
									chkTarget = game.getTarget();
									break;
								}
							}
						} else {
							while (game.getSumB() == 4 || game.getSumB() == 14 || game.getSumB() == 22
									|| game.getSumB() == 32) {
								int random = view.showRandomGame();
								int[] randomValues = { -4, -3, -2, -1, 1, 2, 3, 4 };

								int rdNum = randomValues[rd.nextInt(randomValues.length)];
								String emote = rdNum > 0 ? "!!!!! 나이사 " : "ㅠㅠ";
								if (random == 0) {
									view.showRandomDice(game.getTarget(), rdNum, emote);
									game.deleteB();
									game.setSum(rdNum);
									game.moveB();
									view.showBoard(game.getBoard());
								} else if (random == 1) {
									view.showOneMore();
									chkChange = false;
									break;
								} else if (random == 2) {
									view.showOneDice(game.getTarget());
									chkDice = false;
									chkTarget = game.getTarget();
									break;
								}
							}

						}
					}

					

//					eventOn = true;

				} else {
					view.showFaild();
					view.showBoard(game.getBoard());
				}
				

				if (game.checkRound()) {
					view.showWinnerTeam(game.getTarget());
					if(game.getTarget().equals("A")) {
						return teamService.getTeamA();
					}else {
						return teamService.getTeamB();
					}
					
				}

				if (chkChange) {
					game.changeTarget();

				}
				

			} else {
				view.showWrongAnswer();

			}
			
		}
	}

}
