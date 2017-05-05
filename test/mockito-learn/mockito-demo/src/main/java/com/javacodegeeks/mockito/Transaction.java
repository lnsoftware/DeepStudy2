package com.javacodegeeks.mockito;

import java.util.Date;

public class Transaction {
	private int serialNumber;
	private Date transactionDate;
	private String transactionType;
	private long amount;

	public Transaction(int serialNumber, Date transactionDate,
			String transactionType, long amount) {
		this.serialNumber = serialNumber;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.amount = amount;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public long getAmount() {
		return amount;
	}

}
