package View;

import java.util.Scanner;

import Model.CapitalQuizVO;

public class CapitalQuizView {

	Scanner sc = new Scanner(System.in);

	// 시작 화면 출력
	public void showWelcome() {
		System.out.println("====================================");
		System.out.println("        ★ 수도 맞추기 ★         ");
		System.out.println("====================================");
		System.out.println("        제한 시간 내 정답 입력!     ");
		System.out.println("====================================");
	}

	// 문제 출력
	public void showQuiz(CapitalQuizVO quiz) {
		System.out.println("\n------------------------------------");
		System.out.println("문제 : " + quiz.getQuestion());
		System.out.println(" 제한시간 : " + quiz.getTime() + "초");
		System.out.println("------------------------------------");
		
	}

	// 사용자 입력
	public String getUserInput() {
		System.out.print("→ 정답 입력 >> ");
		return sc.nextLine();
	}

	// 정답 여부 출력
	public void showResult(boolean correct) {
		//이펙트 추가버전
		if (correct) {
			ConsoleFX.frogJumpToGoalAscii(); 
			System.out.println("\n★ 정답입니다! 말을 앞으로 이동합니다! ★");
			System.out.println("====================================");
		} else {
			ConsoleFX.sadRain(12);
			System.out.println("\n ※ 오답입니다... 제자리입니다. ※ ");			
			System.out.println("====================================");
		}

	}


	// 문제가 없을 때
	public void showNoQuestion() {
		System.out.println("! 문제가 존재하지 않습니다 !");
	}

	public void showTimeout() {
		
	}

	public void showRemainChance(int i) {
		System.out.println("남은 정답 입력횟수 : " + i);
		
	}
	
	
}