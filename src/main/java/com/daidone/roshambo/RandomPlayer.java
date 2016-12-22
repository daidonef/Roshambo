package com.daidone.roshambo;

import java.util.Random;

public class RandomPlayer extends Player{

	@Override
	public void generateRoshambo(StringBuffer str1) {
		
		Random random = new Random();
		int randNum = random.nextInt(3);
		Roshambo rRPS;
		
		if (randNum == 0) {
			
			str1.append("Rock");
			rRPS = RPS.gettingRoshambo(str1);
			
		} else if (randNum == 1) {
			
			str1.append("Paper");
			rRPS = RPS.gettingRoshambo(str1);
			
		} else {
			
			str1.append("Scissors");
			rRPS = RPS.gettingRoshambo(str1);
			
		}
		
		setRoshambo (rRPS);
		
	}

}
