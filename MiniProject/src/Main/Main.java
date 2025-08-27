package Main;

import java.util.Scanner;

import Controller.GameController;
import Controller.MemberController;
import Model.Game;
import Model.TeamService;
import View.GameView;
import View.MemberDAO;
import View.MemberView;

public class Main {

	public static void main(String[] args) {
		var sc = new Scanner(System.in);
		var game = new Game();
		var gameView = new GameView();
		var memberView = new MemberView();
		var memberDao = new MemberDAO();
		
		boolean shutDown = false;
		
		
		
		
		boolean chkExit = false;
		
		outer : while(!shutDown) {
			var teamService = new TeamService(memberDao);
			var memberCont = new MemberController(memberView, memberDao,teamService);
			
			inner : while(!chkExit) {
				var gameCont = new GameController(gameView,game,teamService);
				if(memberCont.run()) {
					memberCont.addPoint(gameCont.gameStart());
				}else {
					break outer;
				}
				while(true) {
					System.out.println("[1] 다시하기 [2] 시작화면으로가기 [3] 게임종료 >> ");
					int input = sc.nextInt();
					if(input == 1) {
						continue inner;
					}else if(input == 2) {
						continue outer;
					}else if(input == 3) {
						
						break outer;
					}else {
						System.out.println("잘못 입력했습니다 다시 입력해주세요.");
					}
				}
				
			}
		}
		System.out.println("게임을 종료합니다~ 이용해주셔서 감사함다");
		
	
		
		
		
		
		

	}

}
