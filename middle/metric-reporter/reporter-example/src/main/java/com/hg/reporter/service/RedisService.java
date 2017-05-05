package com.hg.reporter.service;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by hg on 2017/2/6.
 */
@Slf4j
@Component
public class RedisService {

    @Autowired
    private MetricRegistry metricRegistry;

    @HystrixCommand
    @Scheduled(cron = "0/1 * * * * ?")
    public void taskSyncRedis() {

//        Meter meter = metricRegistry.meter("task.syncRedis");
//        meter.mark();

        try {
            Thread.sleep(100 + RandomUtils.nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread current = Thread.currentThread();
//        log.info("task" + current.getId() + ",name:" + current.getName());
    }

}
