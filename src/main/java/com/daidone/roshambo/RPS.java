package com.daidone.roshambo;

public class RPS {
	
	public static Roshambo gettingRoshambo (StringBuffer str2) {
		
		Roshambo RPS = null;
		
		String str1 = str2.toString();
		
		if (str1.equals("Rock")) {
			RPS = RPS.ROCK;
		} else if (str1.equals("Paper")) {
			RPS = RPS.PAPER;
		} else {
			RPS = RPS.SCISSORS;
		}
		
		return RPS;
	}

}
