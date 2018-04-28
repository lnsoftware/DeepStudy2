package com.zhiyin.spring.aop.demo4;

import com.zhiyin.spring.aop.demo4.service.PersonService;
import com.zhiyin.spring.aop.demo4.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

	@Test
	public void AopXmlTest(){
		ApplicationContext cxt = new ClassPathXmlApplicationContext("aopDemo.xml");
		UserService userService = (UserService) cxt.getBean("userService");
		userService.hello();
	}

}
