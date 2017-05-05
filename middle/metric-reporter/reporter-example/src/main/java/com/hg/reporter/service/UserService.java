package com.hg.reporter.service;

import com.codahale.metrics.MetricRegistry;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by hg on 2017/1/23.
 */
@Slf4j
@Component
public class UserService {

    @Autowired
    MetricRegistry metricRegistry;

    public Long getUserInfo(Long uid) {

        int val = RandomUtils.nextInt(100);

        if (val < 4) {
            try {
                Thread.sleep(1000 + RandomUtils.nextInt(1000));
            } catch (InterruptedException e) {
                log.error("time out");
            }
        }

        if (val > 5 && val < 7) {
            log.error("biz error.");
            throw new RuntimeException("task error");
        }

        try {
            Thread.sleep(RandomUtils.nextInt(900));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return uid;
    }

}
