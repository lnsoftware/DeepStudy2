package com.javacodegeeks.mockito;

public class AccountManager {	

	public static AccountSummary getSummary(AccountHolder accountHolder) {
		throw new UnsupportedOperationException();
	}
	
	public static Transaction[] getTransactions(AccountHolder accountHolder) {
		throw new UnsupportedOperationException();
	}

}
