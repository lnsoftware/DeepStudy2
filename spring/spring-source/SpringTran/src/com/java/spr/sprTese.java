package com.java.spr;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext03.xml")
public class sprTese {

	@Resource(name="accountService")
	private AccountService as;
	
	
	@Test
	public void demo2(){
		as.transfer("aaa", "bbb", 200d);
	}
}
