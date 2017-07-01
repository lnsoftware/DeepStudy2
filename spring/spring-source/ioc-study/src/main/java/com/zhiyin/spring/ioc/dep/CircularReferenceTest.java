package com.zhiyin.spring.ioc.dep;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by hg on 2017/6/29.
 */
public class CircularReferenceTest {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(CircularReferenceTest.class.getPackage().getName());

//        Car car = context.getBean(Car.class);
//        System.out.println(car.getName());

        FirstService firstService = context.getBean(FirstService.class);
        System.out.println(firstService.hello("HG"));
    }
}
