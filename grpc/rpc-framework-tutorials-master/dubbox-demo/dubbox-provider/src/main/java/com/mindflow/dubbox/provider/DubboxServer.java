package com.mindflow.dubbox.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class DubboxServer {

    public static void main( String[] args ) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:dubbox-demo-provider.xml");
        context.start();
        System.in.read(); // press any key to exit
    }
}
