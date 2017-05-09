package com.java.spring04;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext04.xml")
public class SpringDemo4 {

	
	@Resource(name="accountService")
	private AccountService acc;
	
	@Test
	public void demo4(){
		
		acc.transfer("aaa", "bbb", 200d);
		
	}
	
}
