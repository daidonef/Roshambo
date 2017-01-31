package com.daidone.roshambo;

import java.util.Random;

public class SmartPlayer extends Player {

	@Override
	public void generateRoshambo(StringBuffer str1) {
		
		Roshambo rps;
		StringBuffer str2 = new StringBuffer();
		
		if (str1.toString().equals("Rock")) {
			
			rps = RPS.gettingRoshambo(str2.append("Paper"));
			
		} else if (str1.toString().equals("Paper")) {
			
			rps = RPS.gettingRoshambo(str2.append("Scissors"));
			
		} else if (str1.toString().equals("Scissors")){
			
			rps = RPS.gettingRoshambo(str2.append("Rock"));
			
		} else {
			
			Random random = new Random();
			int randNum = random.nextInt(3);
			
			if (randNum == 0) {
				
				str1.delete(0, 10);
				str1.append("Rock");
				rps = RPS.gettingRoshambo(str1);
				
			} else if (randNum == 1) {
				
				str1.delete(0, 10);
				str1.append("Paper");
				rps = RPS.gettingRoshambo(str1);
				
			} else {
				
				str1.delete(0, 10);
				str1.append("Scissors");
				rps = RPS.gettingRoshambo(str1);
				
			}
		}
		
		setRoshambo (rps);
	}

}
