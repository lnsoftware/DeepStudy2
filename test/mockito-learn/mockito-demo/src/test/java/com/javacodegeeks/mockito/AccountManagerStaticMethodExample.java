package com.javacodegeeks.mockito;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
public class AccountManagerStaticMethodExample {
	private AccountHolder accountHolder = new AccountHolder("Joe", "123");

	@PrepareForTest({ AccountManager.class })
	@Test
	public void stubStaticMethodToGetMiniStatement() {
		long balance = 9999L;
		AccountSummary accountSummary = new AccountSummary(accountHolder, balance);
		
		p("Call mockStatic AccountManager.class to enable static mocking");
		PowerMockito.mockStatic(AccountManager.class);
		
		p("Stub static method AccountManager.getSummary");
		PowerMockito.when(AccountManager.getSummary(accountHolder))
				.thenReturn(new AccountSummary(accountHolder, 9999));
		
		// Run
		p("Let's get the mini-statement");
		Statement statement = accountHolder.getMiniStatement();
		
		p("Verify the account summary details are correct");
		Assert.assertEquals(accountSummary.getAccountHolder(), statement.getAccountSummary().getAccountHolder());
		Assert.assertEquals(accountSummary.getCurrentBalance(), statement.getAccountSummary().getCurrentBalance());
		
		p("Verify AccountManager.getTransactions was called");
		
		PowerMockito.verifyStatic();
		AccountManager.getTransactions(accountHolder);		
	}

	private void p(String s) {
		System.out.println(s);
	}
}