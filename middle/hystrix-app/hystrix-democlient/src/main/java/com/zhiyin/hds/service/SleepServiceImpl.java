package com.zhiyin.hds.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SleepServiceImpl {

    @HystrixCommand(
            fallbackMethod = "fallback", commandKey = "sleepService", groupKey = "sleepService")
    public String sleep(int sleepTime) {

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (Math.random() > 0.98) {
            throw new RuntimeException("random failure loading payment information over network");
        }

        try {
            if (Math.random() > 0.96) {
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "ok.";
    }

    @HystrixCommand(commandKey = "hello", groupKey = "hello")
    public String hello() {

        /* simulate performing network call to retrieve order */
        try {
            Thread.sleep((int) (Math.random() * 20) + 5);
        } catch (InterruptedException e) {
            // do nothing
        }

        /* fail rarely ... but allow failure */
        if (Math.random() > 0.99) {
            throw new RuntimeException("random failure loading payment information over network");
        }

        /* latency spike 2% of the time */
        if (Math.random() > 0.99) {
            // random latency spike
            try {
                Thread.sleep((int) (Math.random() * 100) + 125);
            } catch (InterruptedException e) {
                // do nothing
            }
        }

        return "ok";
    }

    public String fallback(int sleepTime, Throwable e) {
        log.info("fallback", e);
        return "Fallback call, seems employee service is down";
    }

}
