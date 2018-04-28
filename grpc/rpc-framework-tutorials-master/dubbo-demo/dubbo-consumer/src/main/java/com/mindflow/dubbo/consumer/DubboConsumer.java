package com.mindflow.dubbo.consumer;

import com.mindflow.api.DemoService;
import com.mindflow.api.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Dubbo Consumer client
 *
 */
public class DubboConsumer {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				 "classpath:dubbo-demo-consumer.xml");
		context.start();

		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
		String hello = demoService.sayHello("ricky"); // 执行远程方法
		System.out.println(hello); // 显示调用结果
		
		User user = demoService.findUserById(15L);
		System.out.println(user); // 显示调用结果
		
	}
}
