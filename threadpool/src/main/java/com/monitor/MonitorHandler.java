package com.monitor;

/** 
 * 监控处理器, 目的是把before和after抽象出来, 以便在{@link MonitorableThreadPoolExecutor}中形成一条监控处理器链 
 *  
 * @author lixiaohui 
 * @date 2016年10月11日 下午7:18:38 
 *  
 */  
public interface MonitorHandler {  
      
    /** 
     * 改监控任务是否可用 
     *  
     * @return 
     */  
    boolean usable();   
      
    /** 
     * 任务执行前回调 
     *  
     * @param thread 即将执行该任务的线程 
     * @param runnable 即将执行的任务 
     */  
    void before(Thread thread, Runnable runnable);    
      
    /** 
     * <pre> 
     * 任务执行后回调 
     * 注意: 
     *     1.当你往线程池提交的是{@link Runnable} 对象时, 参数runnable就是一个{@link Runnable}对象 
     *     2.当你往线程池提交的是{@link java.util.concurrent.Callable<?>} 对象时, 参数runnable实际上就是一个{@link java.util.concurrent.FutureTask<?>}对象 
     *       这时你可以通过把参数runnable downcast为FutureTask<?>或者Future来获取任务执行结果 
     *        
     * @param runnable 执行完后的任务 
     * @param throwable 异常信息 
     */  
    void after(Runnable runnable, Throwable throwable);  
      
    /** 
     * 线程池关闭后回调 
     *  
     * @param largestPoolSize 
     * @param completedTaskCount 
     */  
    void terminated(int largestPoolSize, long completedTaskCount);  
}  