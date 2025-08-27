package View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.MemberVO;

public class MemberView {

	public static void main(String[] args) {

	}

	Scanner sc = new Scanner(System.in);

	public void intro() {
		System.out.println();
		System.out.println("===========================================");
		System.out.println();
		System.out.println("  🎉 헬로우 월드에 오신 걸 환영합니다! 🎉");
		System.out.println();
		System.out.println("===========================================");
		System.out.println("");
		System.out.println("※ 게임을 시작하기 위해 팀을 구성해주세요 ※");
	}

	public int showMenu() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("[1] 유저등록 [2] 게임실행 [3] 팀 현황 보기 [4] 포인트랭킹 확인 [5] 게임종료>> ");
		
		int input = sc.nextInt();
		return input;
	}

	// 회원가입 화면
	public MemberVO showRegister() {
		System.out.println();
		System.out.println("===== 유저등록 =====");
		System.out.print("닉네임 입력: ");
		String nick = sc.next();
		return new MemberVO(nick);

	}

	public void showStatusLogin(MemberVO existing) {
		if (existing != null) { // 로그인 데이터 정보 비교 DB에 데이터 있을시
			System.out.println("랭킹에 등록되어있는 유저입니다. 기존유저 정보를 불러옵니다.!!");
			System.out.print("불러오는중.");
			for (int i = 0; i < 2; i++) {
				try {
					Thread.sleep(500);
					if (i == 1) {
						System.out.println(" . ");
					} else {
						System.out.print(" . ");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(existing.getNickname() + "님 환영합니다! 현재 랭킹포인트 : " + existing.getPoint());
		}
	}

	// 팀선택 화면

	public int showSelectTeam() {
		System.out.println();
		System.out.println("===== 팀 선택 =====");
		System.out.print("[1] A팀 [2] B팀 >> ");
		int team = sc.nextInt();
		return team;
	}
	// 팀 배정 완료 화면
	public void assignMessage() {
		System.out.println();
		System.out.println("팀 배정이 완료되었습니다.");
	}
	public void showTeam(List<MemberVO> teamA, List<MemberVO> teamB) {
		System.out.println();
		System.out.println();
		System.out.println("< 현재 팀 현황 >");
		System.out.print("Team A →  ");
		for (MemberVO m : teamA) {
			System.out.print(m.getNickname() + " ");
		}
		System.out.println(teamA.size() > 0 ? "(총 " + teamA.size() + "명)" : "현재 비어있습니다");
		System.out.print("Team B →  ");
		for (MemberVO m : teamB) {
			System.out.print(m.getNickname() + " ");
		}
		System.out.println(teamB.size() > 0 ? "(총 " + teamB.size() + "명)" : "현재 비어있습니다");
	}

	public void showRanking(List<MemberVO> list) {
		System.out.println();
		System.out.println("====랭킹====");
		int rank = 1;

		for (MemberVO m : list) {
			System.out.println(rank + "위: " + m.getNickname() + " - " + m.getPoint() + "점");
			rank++;
		}

	}

	public void showStatusRegister(MemberVO existing) {
		System.out.print("신규유저 등록중.");
		for (int i = 0; i < 2; i++) {
			try {
				Thread.sleep(500);
				if (i == 1) {
					System.out.println(" . ");
				} else {
					System.out.print(" . ");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(existing.getNickname() + "님 환영합니다! 현재 랭킹 포인트 : " + existing.getPoint());
	}

	public void showReady() {
		System.out.print("게임 실행 준비중.");
		for (int i = 0; i < 3; i++) {
			try {
				System.out.print(".");
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}
		System.out.println();

	}
	
	public void matchMessage() {
		
	}

	public void statusTeam(List<MemberVO> list, List<MemberVO> list2) {
		System.out.println("게임 시작 실패!!");
		System.out.println("팀 인원 수를 확인 해주세요! ");
		System.out.println("Team A : " + list.size() + "명");
		System.out.println("Team B : " + list2.size() + "명");

	}

	public void showThrowAdd(String name) {
		System.out.println("이미 " + name +"님은 팀에 속해있습니다");
	}

	public void showWinners(ArrayList<MemberVO> list) {
		System.out.print("게임에 승리하여 <");
		for(int i=0; i<list.size(); i++) {
			if(i==list.size()-1) {
				System.out.print(list.get(i).getNickname());
			}else {
			System.out.print(list.get(i).getNickname() + ", ");
			
		}
		System.out.println("> 에게 각 10점씩 랭킹포인트가 상승합니다");
		
	}
}
}
