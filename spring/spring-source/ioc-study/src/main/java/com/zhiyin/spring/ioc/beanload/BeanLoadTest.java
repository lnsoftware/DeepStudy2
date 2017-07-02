package com.zhiyin.spring.ioc.beanload;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by hg on 2017/6/29.
 */
public class BeanLoadTest {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(BeanLoadTest.class.getPackage().getName());
        context.getBean(DemoService.class);
    }
}
