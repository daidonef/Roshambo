package com.daidone.roshambo;

public class Account {
	
	private int ID;
	private String userName;
	private String firstName;
	private String lastName;
	
	public Account() {
		
	}

	public int getID() {
		return ID;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
