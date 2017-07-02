package com.hg.tp.task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author onlinetechvision.com
 * @version 1.0.0
 * @since 17 Oct 2011
 */
public class TestThreadPoolExecutorService implements ITestThreadPoolExecutorService {

    TestRejectedExecutionHandler testRejectedExecutionHandler;
    private int corePoolSize;
    private int maxPoolSize;
    private long keepAliveTime;
    private int queueCapacity;

    public ThreadPoolExecutor createNewThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(getCorePoolSize(),
            getMaxPoolSize(),
            getKeepAliveTime(),
            TimeUnit.SECONDS,
            new ArrayBlockingQueue(getQueueCapacity()),
            getTestRejectedExecutionHandler());
        return executor;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public TestRejectedExecutionHandler getTestRejectedExecutionHandler() {
        return testRejectedExecutionHandler;
    }

    public void setTestRejectedExecutionHandler(TestRejectedExecutionHandler testRejectedExecutionHandler) {
        this.testRejectedExecutionHandler = testRejectedExecutionHandler;
    }
}