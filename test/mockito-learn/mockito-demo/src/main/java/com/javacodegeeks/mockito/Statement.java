package com.javacodegeeks.mockito;

public class Statement {
	private AccountSummary accountSummary;
	private Transaction[] transactions;
	
	public Statement(AccountSummary accountSummary,
			Transaction[] transactions) {
		this.accountSummary = accountSummary;
		this.transactions = transactions;
	}

	public AccountSummary getAccountSummary() {
		return accountSummary;
	}

	public Transaction[] getTransactions() {
		return transactions;
	}	
	
}
