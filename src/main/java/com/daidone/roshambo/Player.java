package com.daidone.roshambo;

public abstract class Player {
	
	private StringBuffer name;
	private Roshambo roshambo;
	
	public abstract void generateRoshambo(StringBuffer str1);

	public StringBuffer getName() {
		return name;
	}
	
	public Roshambo getRoshambo() {
		return roshambo;
	}
	
	public void setName(StringBuffer name) {
		this.name = name;
	}
	
	public void setRoshambo(Roshambo roshambo) {
		this.roshambo = roshambo;
	}
	
}
