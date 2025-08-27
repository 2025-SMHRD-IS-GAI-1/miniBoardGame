package Controller;

import Model.OddEvenGame;
import View.OddEvenGameView;

public class OddEvenGameController implements MiniGameController{

	private OddEvenGameView view;
	private OddEvenGame model;

	public OddEvenGameController(OddEvenGameView view, OddEvenGame model) {
		this.view = view;
		this.model = model;
	}
	
	public boolean gameStart() {
		 view.intro();
	      boolean result = false;

	      while (true) {
	      
	         int input = 0;
	      
	      try {
	         input = view.oddEvenPick();
	         
	         if (input != 1 && input != 2) {
	            view.inputerror();
	            continue;
	         }
	      } catch (Exception e) {
	         view.inputerror();
	         continue;
	      }

	         

	         model.randomPick();

	         view.effect();
	         
	         result = view.result(model.getNumber(), model.oddEvenSearch(input));

	         break;
	      }
	      return result;
	}

}
