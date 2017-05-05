package com.javacodegeeks.mockito;

public class AccountHolder {
	private String name;
	private String accountNumber;

	public AccountHolder(String name, String accountNumber) {
		this.name = name;
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public Statement getMiniStatement() {
		AccountSummary accountSummary = AccountManager.getSummary(this);
		Transaction[] transactions = AccountManager.getTransactions(this);
		return new Statement(accountSummary, transactions);
	}
}
