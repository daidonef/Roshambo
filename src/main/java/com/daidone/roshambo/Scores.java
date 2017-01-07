package com.daidone.roshambo;

public class Scores {
	
	private int scoresID;
	private int accountID;
	private String opponent;
	private int wins;
	private int loses;
	private int ties;
	
	public Scores() {
		
	}

	public int getScoresID() {
		return scoresID;
	}

	public int getAccountID() {
		return accountID;
	}

	public String getOpponent() {
		return opponent;
	}

	public int getWins() {
		return wins;
	}

	public int getLoses() {
		return loses;
	}

	public int getTies() {
		return ties;
	}

	public void setScoresID(int scoresID) {
		this.scoresID = scoresID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public void setLoses(int loses) {
		this.loses = loses;
	}

	public void setTies(int ties) {
		this.ties = ties;
	}
	
	public static Scores addingWinLoseTie (Scores scores, StringBuffer outcome) {
		
		String str1 = outcome.toString();
		
		if (str1.equals("Win")) {
			scores.setWins(scores.getWins() + 1);
		} else if (str1.equals("Lose")) {
			scores.setLoses(scores.getLoses() + 1);
		} else {
			scores.setTies(scores.getTies() + 1);
		}
		
		return scores;
		
	}
	
	public static Scores newWinLoseTie (Scores scores, StringBuffer outcome) {
		
		String str1 = outcome.toString();
		
		if (str1.equals("Win")) {
			scores.setWins(1);
		} else if (str1.equals("Lose")) {
			scores.setLoses(1);
		} else {
			scores.setTies(1);
		}
		
		return scores;
		
	}

}
