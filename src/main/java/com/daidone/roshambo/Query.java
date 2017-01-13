package com.daidone.roshambo;

public class Query {
	
	public static String gettingScoresAccID (int accID) {
		String query = "FROM Scores WHERE (accountid = " + accID + " )";
		return query;
	}
	
	public static String gettingScoresAccIDOpp (int accID, String opp) {
		String query = "FROM Scores Where (accountid = '" + accID + "') and ("
				+ "opponent = '" + opp + "')";
		return query;
	}
	
	public static String gettingAccountUNaP (StringBuffer un, StringBuffer p) {
		String query = "FROM Account WHERE (username = '" + un + "') and (password = '"
				+ p + "') ";
		return query;
	}
	
	public static String gettingAccountUN (StringBuffer un) {
		String query = "FROM Account WHERE (username = '" + un + "')";
		return query;
	}
	
	public static String gettingAccountID (int id) {
		String query = "FROM Account Where (id = " + id + ")";
		return query;
	}
	
	public static String searchName (StringBuffer n) {
		String query = "FROM Account WHERE (firstname like '%" + n 
				+ "%') or (lastname like '%" + n + "%')";
		return query;
	}

}
