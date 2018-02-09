package com.zhiyin.spring.aop.demo4;

import com.zhiyin.spring.aop.demo4.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAOPTest {
	
	public static void main(String[] args) {
		ApplicationContext cxt = new AnnotationConfigApplicationContext("com.zhiyin.spring.aop.demo4.service");
		PersonService personService = cxt.getBean(PersonService.class);
		try{
			personService.save("zhangsan");
		} catch(Exception e){
			System.out.println("exception catched!");
		}
	}
}
