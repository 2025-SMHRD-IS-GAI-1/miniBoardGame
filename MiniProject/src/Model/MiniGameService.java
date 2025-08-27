package Model;

import java.util.Random;

import Controller.CapitalQuizController;
import Controller.MiniGameController;
import Controller.NonsenseQuizController;
import Controller.OddEvenGameController;
import Controller.UpDownGameController;
import View.CapitalQuizView;
import View.NonsenseQuizView;
import View.OddEvenGameView;
import View.UpDownView;

public class MiniGameService {
		Random rd = new Random();
	public MiniGameController pickRandomMiniGame() {
		int random = rd.nextInt(4)+1;
		MiniGameController game = null;
		switch(random) {
		case 1 : 
			game = new OddEvenGameController(new OddEvenGameView(), new OddEvenGame());
			break;
		case 2 : 
			game = new UpDownGameController(new UpDownGame(), new UpDownView());
			break;
		case 3 : 
			game = new NonsenseQuizController(new NonsenseQuizView(), new NonsenseDAO());
			break;
		case 4 : 
			game = new CapitalQuizController(new CapitalQuizView(), new CapitalDAO());
			break;
		}
		return game;
	}
}
