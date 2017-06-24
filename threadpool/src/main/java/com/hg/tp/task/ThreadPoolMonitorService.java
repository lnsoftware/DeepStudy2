package com.hg.tp.task;

import java.util.concurrent.ThreadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author onlinetechvision.com
 * @since 17 Oct 2011
 * @version 1.0.0
 *
 */
@Slf4j
public class ThreadPoolMonitorService implements IThreadPoolMonitorService {
 
//    private static Logger log = Logger.getLogger(ThreadPoolMonitorService.class);
    ThreadPoolExecutor executor;
    private long monitoringPeriod; 
 
    public void run() {
        try {
            while (true){
                monitorThreadPool();
                Thread.sleep(monitoringPeriod*1000);
            } 
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
 
    public void monitorThreadPool() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("CurrentPoolSize : ").append(executor.getPoolSize());
        strBuff.append(" - CorePoolSize : ").append(executor.getCorePoolSize());
        strBuff.append(" - MaximumPoolSize : ").append(executor.getMaximumPoolSize());
        strBuff.append(" - ActiveTaskCount : ").append(executor.getActiveCount());
        strBuff.append(" - CompletedTaskCount : ").append(executor.getCompletedTaskCount());
        strBuff.append(" - TotalTaskCount : ").append(executor.getTaskCount());
        strBuff.append(" - isTerminated : ").append(executor.isTerminated());
 
        log.debug(strBuff.toString());
    }
 
    public ThreadPoolExecutor getExecutor() {
        return executor;
    }
 
    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }   
 
    public long getMonitoringPeriod() {
        return monitoringPeriod;
    }
 
    public void setMonitoringPeriod(long monitoringPeriod) {
        this.monitoringPeriod = monitoringPeriod;
    }
}