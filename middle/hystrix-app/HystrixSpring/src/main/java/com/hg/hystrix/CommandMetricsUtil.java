package com.hg.hystrix;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.util.HystrixRollingNumberEvent;

import java.util.HashMap;

/**
 * Created by hg on 2016/4/15.
 */
public class CommandMetricsUtil {

    public static MetricsMap getHystrixMetrics(String commandkeyStr) {
        MetricsMap metricsMap = new MetricsMap();
        HystrixCommandKey key = HystrixCommandKey.Factory.asKey( commandkeyStr );
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
}
