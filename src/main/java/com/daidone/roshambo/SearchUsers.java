package com.daidone.roshambo;

import java.util.List;

public class SearchUsers {
	
	public static List<Account> searchName (StringBuffer name) {
		
		List<Account> accounts = DAOAccount.getAccount(Query.searchName(name));
		
		return accounts;
		
	}

}
