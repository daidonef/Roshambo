package com.daidone.roshambo;

public class Rock extends Player{

	@Override
	public void generateRoshambo(StringBuffer str1) {
		
		Roshambo rps = RPS.gettingRoshambo(str1);
		
		setRoshambo (rps);
	}
	
}
