package com.monitor.jmx;

import com.google.common.collect.Lists;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2017/6/24.
 */
public class Tester {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        ExecutorService executorService = context
                .getBean("executorService",ExecutorService.class);

        for (int i = 0; i < 1000000 ; i++) {
            executorService.execute(new DemoIOTask());
            TimeUnit.MILLISECONDS.sleep(50);
        }
        try {
            Thread.sleep(1000 * 60 * 5);
        } catch (final Throwable t) {
        }

        executorService.shutdown();
    }
}
