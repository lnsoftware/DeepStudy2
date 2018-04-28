/*
 * Copyright 2002-2004 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 

package com.zhiyin.spring;

import com.alibaba.fastjson.JSON;
import com.zhiyin.spring.advice.HelloBeforeAdvice;
import com.zhiyin.spring.lifecycle.LifecycleBean;
import com.zhiyin.spring.service.User;
import com.zhiyin.spring.service.UserService;
import com.zhiyin.spring.service.UserServiceImpl;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class ClasspathXmlApplicationContextTests extends TestCase {


	public void testMultiple() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "demoContext.xml" });
		User user = (User) ctx.getBean("user", User.class);
		System.out.println(JSON.toJSONString(user));
	}

	// 测试proxyfactory怎么工作
	public void testMultiple2() throws Exception {

		UserService userService = new UserServiceImpl();

		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setProxyTargetClass(false);
		proxyFactory.setTarget(userService);
		proxyFactory.setInterfaces(new Class[]{UserService.class});
		proxyFactory.addBeforeAdvice(new HelloBeforeAdvice());

		UserService proxy = (UserService) proxyFactory.getProxy();
		log.info( proxy.hello() );

	}


}
