package com.mindflow.motan.consumer;

import com.mindflow.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class MotanClient {

    public static void main( String[] args ) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:motan-demo-consumer.xml");

        DemoService demoService = (DemoService) context.getBean("demoService");
        demoService.sayHello("ricky");
    }
}
