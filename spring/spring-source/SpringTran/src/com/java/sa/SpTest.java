package com.java.sa;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext02.xml")
public class SpTest {


	//@Resource(name="accountService")
	@Resource(name="accountServiceProxy")
	private AccountService ac;
	
	@Test
	public void demo1(){
		ac.transfer("aaa", "bbb", 200d);
	}
}
