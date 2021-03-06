package com.hg.tp.task;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author onlinetechvision.com
 * @version 1.0.0
 * @since 17 Oct 2011
 */
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Starter starter = (Starter) context.getBean("Starter");
        starter.start();
    }
}