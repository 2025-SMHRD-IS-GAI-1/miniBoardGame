package Controller;

import java.util.Scanner;

import Model.UpDownGame;
import View.UpDownView;

public class UpDownGameController implements MiniGameController{

	private UpDownGame model;
    private UpDownView view;
    private Scanner sc;

    public UpDownGameController(UpDownGame model, UpDownView view) {
        this.model = model;
        this.view = view;
        this.sc = new Scanner(System.in);
    }

    public boolean gameStart() {
        view.showWelcome();
        System.out.println("== 첫 번째 도전! 숫자를 입력하세요 == ");
        boolean chk = false;
        while(!model.isGameOver()) {
            int userNum = sc.nextInt();
            String result = model.checkGuess(userNum);
           chk = model.isCorrect(userNum);

            if(result.equals("CORRECT")) {
                view.showCorrect();
                try {
    				Thread.sleep(3000);
    			} catch (Exception e) {
    				
    			}
                break;
            } else {
                view.showMessage(result, model.getChance());
            }
        }

        if(model.isGameOver()) {
            view.showAnswer(model.getAnswer());
            try {
				Thread.sleep(3000);
			} catch (Exception e) {
				
			}
        }
        return chk;
    }
	
} 
