package View;

import java.util.Random;
import java.util.Scanner;

public class OddEvenGameView {

	// 랜덤도구 , 입력도구 꺼내오기

	Scanner sc = new Scanner(System.in);

	public void intro() {
		System.out.println(" ████████████████████████████████████");
		System.out.println("██                                  ██");
		System.out.println("██     ※  알쏭달쏭미니게임  ※     ██");
		System.out.println("██      오늘운을 시험해보세요       ██"); 
		System.out.println("██                                  ██");
		System.out.println(" ████████████████████████████████████");
	}

	public int oddEvenPick() {
		// 유저 홀수 짝수 입력받기
		System.out.println();
		System.out.println("다음 숫자는 홀수일까요 짝수일까요??");
		System.out.println(" ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇   " );
		System.out.println("▇                                                                         ▇");
		System.out.println("▇           ██████╗   ██████╗                                             ▇");
	    System.out.println("▇  ██████╗  ██╔══██╗  ██╔══██╗      ███████╗██╗   ██╗███████╗███╗   ██╗   ▇");
	    System.out.println("▇ ██╔═══██╗ ██║  ██║  ██║  ██║      ██╔════╝██║   ██║██╔════╝████╗  ██║   ▇ ");
	    System.out.println("▇ ██║   ██║ ██║  ██║  ██║  ██║      █████╗  ██║   ██║█████╗  ██╔██╗ ██║   ▇");
	    System.out.println("▇ ██║   ██║ ██║  ██║  ██║  ██║      ██╔══╝  ╚██╗ ██╔╝██╔══╝  ██║╚██╗██║   ▇");
	    System.out.println("▇ ╚██████╔╝ ██████╔╝  ██████╔╝      ███████╗╚ ████╔╝ ███████╗██║ ╚████║   ▇");
	    System.out.println("▇  ╚═════╝  ╚═════╝   ╚═════╝       ╚══════╝  ╚═══╝  ╚══════╝╚═╝  ╚═══╝   ▇");
		System.out.println("▇           ① 홀수                             ② 짝수                   ▇");
		System.out.println(" ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇ ▇  ");
		System.out.println(); 
		System.out.println("정답은 >> ");
		
		  try {
		         return sc.nextInt();
		      } catch (Exception e) {
		         sc.nextLine();
		         throw e;
		      }
	}

	public void effect() {
		System.out.println();
		String[] effects = { "두", "근", "세", "근" };
		for (String s : effects) {
			System.out.print(s);
			try {
				Thread.sleep(500); // 0.5초 딜레이
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("... 결과는!?");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

	}

	public boolean result(int number, boolean oddEven) {
		String result = number % 2 == 1? "홀수" : "짝수";
		boolean chk = false;
		if (oddEven) {
			System.out.print("👏👏👏오오~ 럭키비키니 자나 아니 안잔다 정답이 야도란!");
			chk = true;
			
		} else {
			System.out.print("😭😭저런 ㅠㅠ 운이 없구나 틀렸어부지리는 클라스~");
			chk = false;
		}
		System.out.println(" 랜덤숫자는 " + number +" "+ result + "야!!!👏👏👏");
		return chk;
	}

	
	public void inputerror() {
		System.out.println("👀오타니야 오타니! 잘못 입력했다구렁이! 다시 입력해줘죠죠의 기묘한 모험..👀");
	}

}
