


package com.hg.reporter;

import com.netflix.hystrix.examples.demo.HystrixCommandDemo;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    HystrixRequestContext context = HystrixRequestContext.initializeContext();

                    try {
                        new HystrixCommandDemo().executeSimulatedUserRequestForOrderConfirmationAndCreditCardPayment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    context.close();
                log.info("hystrix demo");
                }
            }
        });
        t.setDaemon(true);
        t.start();


    }

    public static void main(String[] args) {

        new ApplicationStartup().onApplicationEvent(null);
    }

}