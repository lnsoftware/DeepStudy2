/**
 * Copyright 2012 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hg.hystrix;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.*;
import com.netflix.hystrix.util.HystrixRollingNumberEvent;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.HashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * The obligatory "Hello World!" showing a simple implementation of a {@link HystrixCommand}.
 */
public class PrintMetricsCommand extends HystrixCommand<String> {

    private final String name;

    public PrintMetricsCommand(String name) {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("printmetrics.group"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("printmetrics.key"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withCircuitBreakerRequestVolumeThreshold(2)
                                        .withCircuitBreakerSleepWindowInMilliseconds(10 * 1000))
        );
        this.name = name;
    }

    @Override
    protected String run() throws InterruptedException {

        TimeUnit.MILLISECONDS.sleep(10000);


        return "Hello " + name + "!";
    }

    @Override
    protected String getFallback() {
        return Thread.currentThread().getName() + "exeucute Falled";
    }



    /**
     * 演示获取Hystrix的Metrics.
     */
    public static MetricsMap getHystrixMetrics() {
        MetricsMap metricsMap = new MetricsMap();
        HystrixCommandKey key = HystrixCommandKey.Factory.asKey("printmetrics.key");
        HystrixCommandMetrics metrics = HystrixCommandMetrics.getInstance(key);

        if (metrics != null) {
            HystrixCommandMetrics.HealthCounts counts = metrics.getHealthCounts();
            HystrixCircuitBreaker circuitBreaker = HystrixCircuitBreaker.Factory.getInstance(key);

            metricsMap.put("circuitOpen", circuitBreaker.isOpen());
            metricsMap.put("totalRequest", counts.getTotalRequests());
            metricsMap.put("errorPercentage", counts.getErrorPercentage());
            metricsMap.put("success", metrics.getRollingCount(HystrixRollingNumberEvent.SUCCESS));
            metricsMap.put("timeout", metrics.getRollingCount(HystrixRollingNumberEvent.TIMEOUT));
            metricsMap.put("failure", metrics.getRollingCount(HystrixRollingNumberEvent.FAILURE));
            metricsMap.put("shortCircuited", metrics.getRollingCount(HystrixRollingNumberEvent.SHORT_CIRCUITED));
            metricsMap.put("threadPoolRejected",
                    metrics.getRollingCount(HystrixRollingNumberEvent.THREAD_POOL_REJECTED));
            metricsMap.put("semaphoreRejected", metrics.getRollingCount(HystrixRollingNumberEvent.SEMAPHORE_REJECTED));
            metricsMap.put("latency50", metrics.getTotalTimePercentile(50));
            metricsMap.put("latency90", metrics.getTotalTimePercentile(90));
            metricsMap.put("latency100", metrics.getTotalTimePercentile(100));
        }

        return metricsMap;
    }

    /**
     * 简易Map，对不存在的统计项返回0.
     */
    public static class MetricsMap extends HashMap {
        @Override
        public Object get(Object key) {
            Object result = super.get(key);
            return result != null ? result : "0";
        }
    }

    public static void main(String[] args) throws Exception{
        for(int i=0;i<100000; i++){
            PrintMetricsCommand command = new PrintMetricsCommand("test-Fallback");
            String result = command.execute();
            System.out.println(JSON.toJSONString(getHystrixMetrics()) );
            System.out.println(result);
        }

    }

}
