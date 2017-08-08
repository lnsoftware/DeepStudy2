package com.blog.hystrix.mdc;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

public class MdcHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
    @Override
    public Callable wrapCallable(Callable callable) {
        return new MdcCallable(callable);
    }
    static class MdcCallable<K> implements Callable<K> {
        private final Callable<K> actual;
        private final Map parentMDC;
        // 主线程执行
        public MdcCallable(Callable<K> actual) {
            this.actual = actual;
            // 保存线程相关信息
            this.parentMDC = MDC.getCopyOfContextMap();
        }
        // 线程池执行
        @Override
        public K call() throws Exception {
            try {
                // 恢复线程信息
                if (parentMDC != null && parentMDC.size() > 0) {
                    MDC.setContextMap(parentMDC);
                }
                // 执行原始逻辑
                return actual.call();
            } finally {
                MDC.clear();
            }
        }
    }
}
