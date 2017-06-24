package com.monitor.jmx;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource
public class ThreadPoolMBean  {

	private ThreadPoolExecutor executorService;
	
    public ThreadPoolMBean(ThreadPoolExecutor service) {
    	executorService = service;
    }

    @ManagedAttribute(description = "Returns the number of threads that execute tasks")
    public int getActiveCount() {
        return executorService.getActiveCount();
    }

    @ManagedAttribute(description = "Returns the total number of completed tasks")
    public long getCompletedTaskCount() {
        return executorService.getCompletedTaskCount();
    }

    @ManagedAttribute(description = "Returns the size of the core pool of threads")
    public int getCorePoolSize() {
        return executorService.getCorePoolSize();
    }

    @ManagedAttribute(description = "Returns the largest number of threads that have been in the pool")
    public int getLargestPoolSize() {
        return executorService.getLargestPoolSize();
    }

    @ManagedAttribute(description = "Returns the max size allowed in the pool of threads")
    public int getMaximumPoolSize() {
        return executorService.getMaximumPoolSize();
    }

    @ManagedAttribute(description = "Returns the number of additional elements that this queue can "
            + "accept without  "
            + "blocking")
    public int getQueueRemainingCapacity() {
        return executorService.getQueue().remainingCapacity();
    }

    @ManagedAttribute(description = "Returns the total number of tasks that have ever been scheduled for execution ")
    public long getTaskCount() {
        return executorService.getTaskCount();
    }

    @ManagedAttribute(description = "Sets the core size of the pool")
    public void setCorePoolSize(int corePoolSize) {
    	executorService.setCorePoolSize(corePoolSize);
    }

    @ManagedAttribute(description = "Sets the max size allowed in the pool of threads")
    public void setMaximumPoolSize(int maximumPoolSize) {
    	executorService.setMaximumPoolSize(maximumPoolSize);
    }
}