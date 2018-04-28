package com.zhiyin.spring.aop.demo4.service.impl;

import com.zhiyin.spring.aop.demo4.service.PersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
	private String username;
	
	public PersonServiceImpl(){
	}
	
	public PersonServiceImpl(String username){
		this.username = username;
	}

	@Override
	public String getUsername() {
		username = "lisi";
		return username;
	}

	@Override
	public final String save(String name) {
		System.out.println("saved!"); 
//		int i = 2/0;
		return "saved!";
	}

}
