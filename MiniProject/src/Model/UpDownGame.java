package Model;

import java.util.Random;
import java.util.Scanner;

public class UpDownGame {
	 Random rd = new Random();
	 private int answer;
	    private int chance;

	    public UpDownGame() {
	        this.answer = rd.nextInt(50) + 1; // 1~50
	        this.chance = 5;
	    }

	    public int getChance() {
	        return chance;
	    }

	    public boolean isCorrect(int userNum) {
	        return userNum == answer;
	    }

	    public boolean isGameOver() {
	        return chance <= 0;
	    }

	    public String checkGuess(int userNum) {
	        chance--;
	        if(userNum > answer) {
	            return "DOWN";
	        } else if(userNum < answer) {
	            return "UP";
	        } else {
	            return "CORRECT";
	        }
	    }

	    public int getAnswer() {
	        return answer;
	    }
		
	
		
		

	

}
