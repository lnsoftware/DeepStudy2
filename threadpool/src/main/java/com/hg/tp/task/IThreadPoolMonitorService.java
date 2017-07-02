package com.hg.tp.task;

import java.util.concurrent.ThreadPoolExecutor;

public interface IThreadPoolMonitorService extends Runnable {

    public void monitorThreadPool();

    public ThreadPoolExecutor getExecutor();

    public void setExecutor(ThreadPoolExecutor executor);
}