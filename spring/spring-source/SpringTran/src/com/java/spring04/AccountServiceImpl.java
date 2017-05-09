package com.java.spring04;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AccountServiceImpl implements AccountService {
	
	//×¢Èë×ªÕËµÄDAO
	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	public void transfer( String out,  String in,  Double money) {
		
		accountDao.outMoney(out, money);
		int i = 1/0;
		accountDao.inMoney(in, money);	
		
	}

}
