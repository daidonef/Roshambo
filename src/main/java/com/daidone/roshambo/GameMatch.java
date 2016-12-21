package com.daidone.roshambo;

public class GameMatch {
	
	public static StringBuffer gameOutcome (Roshambo hRPS, Roshambo oRPS) {
		
				StringBuffer outcome = new StringBuffer();
		
		if (hRPS == Roshambo.ROCK) {
			if (oRPS == Roshambo.ROCK) {
				outcome.append("Tie");	
			} else if (oRPS == Roshambo.PAPER) {
				outcome.append("Lose");
			} else {
				outcome.append("Win");
			}
		} else if (hRPS == Roshambo.PAPER) {
			if (oRPS == Roshambo.ROCK) {
				outcome.append("Win");	
			} else if (oRPS == Roshambo.PAPER) {
				outcome.append("Tie");
			} else {
				outcome.append("Lose");
			}
		} else {
			if (oRPS == Roshambo.ROCK) {
				outcome.append("Lose");	
			} else if (oRPS == Roshambo.PAPER) {
				outcome.append("Win");
			} else {
				outcome.append("Tie");
			}
		}
		
		return outcome;
	}

}
