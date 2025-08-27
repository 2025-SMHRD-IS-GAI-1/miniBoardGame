package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	Scanner sc = new Scanner(System.in);
	Random rd = new Random();
	int sumA;;
	int sumB;;
	int goal;
	int posA[] = { 0, 2};
	int posB[] = { 1, 2};
	int roundA;
	int roundB;
	int sum;
	ArrayList<int[]> location = new ArrayList<>();

	String target = "";
	String quiz[] = { "넌센스 퀴즈", "숫자 맞추기", "수도 맞추기", "홀짝 맞추기" };
	String board[][] = { //
			{ "    ", "    ", "a", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x" },
			{ "    ", "    ", "b", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x" }, // 2번째 인덱스부터 11번째 인덱스까지 사용하고 겹칠시
			{ "    ", " ", "Start  ", "□  ", "□  ", "□  ", "？  ", "□  ", "□  ", "□  ", "□  ", "♠", "x" },
			{ "    ", "x", "□  ", "┌--", "----", "----", "-", "----", "----", "----", "---┐", "  □", "x" },
			{ "    ", "x", "□  ", "│", "    ", "    ", "    ", "    ", "    ", "      ", "│  ", "□", "x" },
			{ "    ", "x", "□  ", "│", "    ", "    ", "  Go", "al :", goal+"     ", "    ", "│  ", "□", "x" },
			{ "    ", "x", "？  ", "│", "    ", "  te", "amA ", "scor", "e : ", "0     ", "│  ", "□", "x" },
			{ "    ", "x", "□  ", "│", "    ", "  te", "amB ", "scor", "e : ", "0     ", "│  ", "？", "x" },
			{ "    ", "x", "□  ", "│", "    ", "    ", "    ", "    ", "    ", "      ", "│  ", "□", "x" },
			{ "    ", "x", "□  ", "│", "    ", "    ", "    ", "    ", "    ", "      ", "│  ", "□", "x" },
			{ "    ", "x", "□  ", "└--", "----", "----", "-", "----", "----", "----", "---┘  ", "□", "x" },
			{ "    ", "x", "♣  ", "□  ", "□  ", "□  ", "□  ", "？  ", "□  ", "□  ", "□  ", "♥", "x" },
			{ "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x" },
			{ "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x" }, };

	

	
	
// 기능 //	


public void locatePath() {
		for (int i = 2; i <= 11; i++) {
			location.add(new int[] { 1, i });
		}
		for (int i = 3; i <= 11; i++) {
			location.add(new int[] { i, 12 });
		}
		for (int i = 10; i >= 2; i--) {
			location.add(new int[] { 12, i });
		}
		for (int i = 10; i >= 3; i--) {
			location.add(new int[] { i, 1 });
		}
	}


	

public void switchBoth() {
if (this.getSumA() == 18) {
	this.deleteB();
	this.deleteA();
	this.setSumA(this.getSumB()); // A위치 0으로 바꾸기
	this.setSumB(18); // B위치 18로 바꾸기
	this.setSum(0);
	this.moveB();
	this.moveA();
} else {
	this.deleteB();
	this.deleteA();
	this.setSumB(this.getSumA());
	this.setSumA(18);
	this.setSum(0);
	this.moveA();
	this.moveB();
}
}





public void showFail() {
	System.out.println("아쉽게도 제한시간을 초과하셨네요 ㅠㅠ!! 말을 움직이지 않습니다.");
}

public void moveA() {


	sumA += sum;

	if (sumA >= 36 && roundA == 2) {
		sumA = 0;
		roundA++;
	} else if (sumA >= 36) {
		sumA %= 36;
		roundA++;
	}

	board[6][9] = roundA + "     ";

	// ──────────── 말추가 ──────────────── //

	if (sumA >= 0 && sumA <= 9) { // 해당 발판에 A팀 말이 존재할 경우
		if (board[1][location.get(sumA)[1]].equals("b")) {
			board[0][location.get(sumA)[1]] = "a";
		} else {
			board[1][location.get(sumA)[1]] = "a";
		}
	} else if (sumA >= 10 && sumA <= 18) {
		if (board[location.get(sumA)[0]][location.get(sumA)[1]].equals("b")) {
			board[location.get(sumA)[0]][location.get(sumA)[1]] = "ba";
		} else {
			board[location.get(sumA)[0]][location.get(sumA)[1]] = "a";
		}

	} else if (sumA >= 19 && sumA <= 27) {
		if (board[12][location.get(sumA)[1]].equals("b")) {
			board[13][location.get(sumA)[1]] = "a";
		} else {
			board[12][location.get(sumA)[1]] = "a";
		}
	} else {
		if (board[location.get(sumA)[0]][location.get(sumA)[1]].equals("b")) {
			board[location.get(sumA)[0]][location.get(sumA)[1]] = "ab";
		} else {
			board[location.get(sumA)[0]][location.get(sumA)[1]] = "a";
		}

	}
	posA[0] = location.get(sumA)[0];
	posA[1] = location.get(sumA)[1];
}

public void moveB() {

	
	sumB += sum;

	if (sumB >= 36 && roundB == 2) {
		sumB = 0;
		roundB++;
	} else if (sumB >= 36) {
		sumB %= 36;
		roundB++;
	}

	board[7][9] = roundB + "     ";;

	if (sumB >= 0 && sumB <= 9) { // 해당 발판에 A팀 말이 존재할 경우
		if (board[1][location.get(sumB)[1]].equals("a")) {
			board[0][location.get(sumB)[1]] = "b";
		} else {
			board[1][location.get(sumB)[1]] = "b";
		}
	} else if (sumB >= 10 && sumB <= 18) {
		if (board[location.get(sumB)[0]][location.get(sumB)[1]].equals("a")) {
			board[location.get(sumB)[0]][location.get(sumB)[1]] = "ab";
		} else {
			board[location.get(sumB)[0]][location.get(sumB)[1]] = "b";
		}

	} else if (sumB >= 19 && sumB <= 27) {
		if (board[12][location.get(sumB)[1]].equals("a")) {
			board[13][location.get(sumB)[1]] = "b";
		} else {
			board[12][location.get(sumB)[1]] = "b";
		}
	} else {
		if (board[location.get(sumB)[0]][location.get(sumB)[1]].equals("a")) {
			board[location.get(sumB)[0]][location.get(sumB)[1]] = "ba";
		} else {
			board[location.get(sumB)[0]][location.get(sumB)[1]] = "b";
		}
	}
	posB[0] = location.get(sumB)[0];
	posB[1] = location.get(sumB)[1];
}

public boolean checkRound() {
	if (roundB >= goal || roundA >= goal) {
		return true;
	}
	return false;
}

public void changeTarget() {
	if (target.equals("A")) {
		target = "B";
	} else {
		target = "A";
	}
}




public void deleteA() {
	String before = board[posA[0]][posA[1]];
	if (sumA >= 0 && sumA <= 9) { // 말이 움직이기전 이전말 삭제하는과정인데 해당말들이 중첩되는위치에있는지 확인하고 위쪽아래쪽 구분해서 조건만들어야함
		if (board[1][posA[1]].equals("a") && board[0][posA[1]].equals("b")) {
			board[0][posA[1]] = "x";
			board[1][posA[1]] = "b";
		} else if (board[0][posA[1]].equals("a") && board[1][posA[1]].equals("b")) {
			board[posA[0]][posA[1]] = "x";
		} else {
			board[1][posA[1]] = "x";
		}
	} else if (sumA >= 19 && sumA <= 27) { // 말이 움직이기전 이전말 삭제하는과정인데 해당말들이 중첩되는위치에있는지 확인하고 위쪽아래쪽 구분해서
											// 조건만들어야함
		if (board[12][posA[1]].equals("a") && board[13][posA[1]].equals("b")) {
			board[13][posA[1]] = "x";
			board[12][posA[1]] = "b";
		} else if (board[12][posA[1]].equals("b") && board[13][posA[1]].equals("a")) {
			board[posA[0]][posA[1]] = "x";
		} else {
			board[posA[0]][posA[1]] = "x";
		}
	}else {
		if (before.equals("a")) {
			board[posA[0]][posA[1]] = "x";
		} else if (before.equals("ab") || before.equals("ba")) {
			board[posA[0]][posA[1]] = "b";
		}
	}
}

public void deleteB() {
	String before = board[posB[0]][posB[1]];

	if (sumB >= 0 && sumB <= 9) { // 말이 움직이기전 이전말 삭제하는과정인데 해당말들이 중첩되는위치에있는지 확인하고 위쪽아래쪽 구분해서 조건만들어야함
		if (board[1][posB[1]].equals("b") && board[0][posB[1]].equals("a")) {
			board[0][posB[1]] = "x";
			board[1][posB[1]] = "a";
		} else if (board[0][posB[1]].equals("b") && board[1][posB[1]].equals("a")) {
			board[posB[0]][posB[1]] = "x";
		} else {
			board[1][posB[1]] = "x";
		}
	} else if (sumB >= 19 && sumB <= 27) { // 말이 움직이기전 이전말 삭제하는과정인데 해당말들이 중첩되는위치에있는지 확인하고 위쪽아래쪽 구분해서
											// 조건만들어야함
		if (board[12][posB[1]].equals("b") && board[13][posB[1]].equals("a")) {
			board[13][posB[1]] = "x";
			board[12][posB[1]] = "a";
		} else if (board[12][posB[1]].equals("a") && board[13][posB[1]].equals("b")) {
			board[posB[0]][posB[1]] = "x";
		} else {
			board[posB[0]][posB[1]] = "x";
		}
	} else {
		if (before.equals("b")) {
			board[posB[0]][posB[1]] = "x";
		} else if (before.equals("ab") || before.equals("ba")) {
			board[posB[0]][posB[1]] = "a";
		}
	}
}





 public int getSumA() {
	return sumA;
}

public int getSumB() {
	return sumB;
}

public int getGoal() {
	return goal;
}

public int[] getPosA() {
	return posA;
}

public int[] getPosB() {
	return posB;
}

public int getRoundA() {
	return roundA;
}

public int getRoundB() {
	return roundB;
}

public int getSum() {
	return sum;
}

public ArrayList<int[]> getLocation() {
	return location;
}

public String getTarget() {
	return target;
}

public String[] getQuiz() {
	return quiz;
}

public String[][] getBoard() {
	return board;
}





public void setSumA(int sumA) {
	this.sumA = sumA;
}

public void setSumB(int sumB) {
	this.sumB = sumB;
}

public void setGoal(int goal) {
	this.goal = goal;
	this.board[5][8] = goal+"     ";
}

public void setPosA(int[] posA) {
	this.posA = posA;
}

public void setPosB(int[] posB) {
	this.posB = posB;
}

public void setRoundA(int roundA) {
	this.roundA = roundA;
}

public void setRoundB(int roundB) {
	this.roundB = roundB;
}

public void setSum(int sum) {
	this.sum = sum;
}

public void setLocation(ArrayList<int[]> location) {
	this.location = location;
}

public void setTarget(String target) {
	this.target = target;
}

public void setQuiz(String[] quiz) {
	this.quiz = quiz;
}

public void setBoard(String[][] board) {
	this.board = board;
}


	


}
