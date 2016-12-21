package com.daidone.roshambo;

public class Rock extends Player{

	@Override
	public void generateRoshambo(StringBuffer str1) {
		
		Roshambo str2 = RPS.gettingRoshambo(str1);
		
		setRoshambo (str2);
	}
	
}
