package Model;

import java.util.Random;

public class OddEvenGame {

	
	private int number;
	Random rd = new Random();
	
	
	public void randomPick() {
	number = rd.nextInt(50)+1;
	}
	
	public boolean oddEvenSearch(int input) {
		return input % 2  == number % 2 ? true : false;
	}
	
	
	
	public int getNumber() {
		return number;
	}
	

	
	
	
	
	
	
	
}
