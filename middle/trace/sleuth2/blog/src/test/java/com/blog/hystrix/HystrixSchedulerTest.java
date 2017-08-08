package com.blog.hystrix;

import com.netflix.hystrix.HystrixThreadPool;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.junit.Test;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class HystrixSchedulerTest {

    @Test
    public void testHystrixScheduler() throws InterruptedException {

        Scheduler scheduler = new HystrixThreadPool.HystrixThreadPoolDefault(HystrixThreadPoolKey.Factory.asKey("demo"), HystrixThreadPoolProperties.defaultSetter()).getScheduler();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(() -> {
            System.out.println(Thread.currentThread().getName()+":run" );
                });

        TimeUnit.MILLISECONDS.sleep(200);
    }

}
