package com.mindflow.motan.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class MotanServer {

    public static void main( String[] args ) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:motan-demo-provider.xml");
        context.start();

        System.in.read(); // press any key to exit
    }
}
