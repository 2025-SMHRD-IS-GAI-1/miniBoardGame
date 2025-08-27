package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.MemberVO;
import Model.TeamService;
import View.MemberDAO;
import View.MemberView;

public class MemberController {

	Scanner sc = new Scanner(System.in);

	private MemberView view;
	private MemberDAO dao;
	private TeamService teamService;

	public MemberController(MemberView view, MemberDAO dao,  TeamService teamService) {
		this.view = view;
		this.dao = dao;
		this.teamService = teamService;

	}

	public boolean run() {
		boolean isRun = true;
		view.intro();
		while (true) {
			
			int input = view.showMenu();

			MemberVO user = null;

			if (input == 1) { // 유저등록
				MemberVO mvo = view.showRegister();
				if(teamService.chkTeam(mvo)) {
					view.showTeam(teamService.getTeamA(), teamService.getTeamB());
					continue;
				}

				MemberVO existing = dao.searchNickname(mvo.getNickname());
				
				if(existing != null) {
					view.showStatusLogin(existing);
				}else {
					int result = dao.register(mvo);
					if(result > 0) {
						view.showStatusRegister(mvo);
					}
					
				}
				
				view.showTeam(teamService.getTeamA(), teamService.getTeamB());

				int team = view.showSelectTeam();

				boolean add = teamService.addTeam(mvo, team);

				if(add) {
					view.assignMessage();
					view.showTeam(teamService.getTeamA(), teamService.getTeamB());
				}else {
					view.showThrowAdd(mvo.getNickname());
				}

				

			} else if (input == 2) {
				// 게임실행
				view.showReady();
				if (!teamService.canStartGame()) {
					view.statusTeam(teamService.getTeamA(),teamService.getTeamB());
					continue;
				}else {
					break;
				}

			} else if (input == 3) {// 팀 현황
				view.showTeam(teamService.getTeamA(), teamService.getTeamB());

			} else if (input == 4) {// 랭킹조회

				List<MemberVO> ranking = dao.rankedSelectAll();
				view.showRanking(ranking);

			} else if (input == 5) { // 종료
				isRun = false;
				break;
			} else {

				System.out.println("잘못된 입력입니다.");
			}
		}
		return isRun;
	}
	
	public void addPoint(ArrayList<MemberVO> list) {
		for(MemberVO m : list) {
			dao.updatePoint(m);
		}
		view.showWinners(list);
	}
	
	
}
