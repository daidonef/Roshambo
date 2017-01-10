package com.daidone.roshambo;

public class Query {
	
	public static String gettingScoresAccID (int accountID) {
		String query = "FROM Scores WHERE (accountid = " + accountID + " )";
		return query;
	}

}
