package View;

public class UpDownView {

	public void showWelcome() {
		  System.out.println("                         ");
	        System.out.println("                         ");
	        System.out.println("                         ");
	        System.out.println("     @@                ");
	        System.out.println("     @@#.          @@.    ");
	        System.out.println("     @@@          @@@    ");
	        System.out.println("    *@@@@       ~@@@@    ");
	        System.out.println("    @@@@@       @@@@@    ");
	        System.out.println("    @@@@@@@@@@@@@@@@@    ");
	        System.out.println("    $@@@@@@@@@@@@@@@@    ");
	        System.out.println("   ,@#$-*@@#@#@@@@@@.    ");
	        System.out.println("   ;@*-:$:@@@@@!~$@@     ");
	        System.out.println("   =@!#@@-@@@@!.$,$@-    ");
	        System.out.println("   =@*~@$-@@@@!$@~~@@    ");
	        System.out.println("   ;@@~--@@@@@==*,@@@    ");
	        System.out.println("   ,@@@@@@@@@@@=*#@@~    ");
	        System.out.println("    -@@@@@@@@@@@@@@@     ");
	        System.out.println("     ,@@@@@@@@@@@@@,     ");
	        System.out.println("       -@@@@@@@@@-       ");
//	        System.out.println("          ~;;;:          ");
//	        System.out.println("                         ");
//	        System.out.println("                         ");
//	        System.out.println("                         ");
		System.out.println("===================================================");
		System.out.println("||                                               ||");
		System.out.println("|| ▲▼▲▼▲▼ 업다운 숫자 게임 ▲▼▲▼▲▼    ||");
		System.out.println("||                                               ||");
		System.out.println("===================================================");
		System.out.println("||                   게임 규칙                   ||");
		System.out.println("||   ▶ 1~50 사이의 숫자 중 정답을 맞추세요!     ||");
		System.out.println("||   ▶ 기회는 총 5번!                           ||");
		System.out.println("===================================================");
		System.out.println();
	}

	public void showMessage(String message, int chance) {
		if (chance > 0) {
			System.out.println();
			System.out.println("❌❌❌");
			System.out.println();
			System.out.println(message + " ( ⚠ 남은 기회  " + chance + "번 ⚠)");
			System.out.println();
			System.out.println(" === 다시 입력하세요 ===  ");
		} else {
			System.out.println("    ★★★ GAME OVER ★★★");
			System.out.println("=== 다음 기회에 도전해주세요 ===");
			
		}
	}

	public void showCorrect() {
		System.out.println();
		System.out.println("　✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨");
		System.out.println(" 　  정답입니다! 축하드립니다");
		System.out.println("     숫자 맞추기 게임 성공!! ");
		System.out.println("　✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨");
		System.out.println();
	}

	public void showAnswer(int answer) {
		
			System.out.println("=== ▶ 정답 :  " + answer + "            ===");
		
		
		
	}

}
