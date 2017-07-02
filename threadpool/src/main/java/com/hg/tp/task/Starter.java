package com.hg.tp.task;

import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author onlinetechvision.com
 * @version 1.0.0
 * @since 17 Oct 2011
 */
public class Starter {
    Logger log = LoggerFactory.getLogger(Starter.class);

    IThreadPoolMonitorService threadPoolMonitorService;
    ITestThreadPoolExecutorService testThreadPoolExecutorService;

    public void start() {

        // A new thread pool is created...
        ThreadPoolExecutor executor = testThreadPoolExecutorService.createNewThreadPool();
        executor.allowCoreThreadTimeOut(true);

        // Created executor is set to ThreadPoolMonitorService...
        threadPoolMonitorService.setExecutor(executor);

        // ThreadPoolMonitorService is started...
        Thread monitor = new Thread(threadPoolMonitorService);
        monitor.start();
        log.info("monitor thread start.");

        // New tasks are executed...
        for (int i = 1; i < 10; i++) {
            executor.execute(new TestTask("Task" + i));
        }

        try {
            Thread.sleep(40000);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        for (int i = 10; i < 19; i++) {
            executor.execute(new TestTask("Task" + i));
        }

        // executor is shutdown...
        executor.shutdown();
    }

    public IThreadPoolMonitorService getThreadPoolMonitorService() {
        return threadPoolMonitorService;
    }

    public void setThreadPoolMonitorService(IThreadPoolMonitorService threadPoolMonitorService) {
        this.threadPoolMonitorService = threadPoolMonitorService;
    }

    public ITestThreadPoolExecutorService getTestThreadPoolExecutorService() {
        return testThreadPoolExecutorService;
    }

    public void setTestThreadPoolExecutorService(ITestThreadPoolExecutorService testThreadPoolExecutorService) {
        this.testThreadPoolExecutorService = testThreadPoolExecutorService;
    }
}