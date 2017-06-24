package com.monitor;

import com.monitor.MonitorHandler;
import com.monitor.MonitorableThreadPoolExecutor;
import org.apache.commons.lang3.RandomUtils;

import java.io.IOException;
import java.util.Map;  
import java.util.concurrent.Callable;  
import java.util.concurrent.CancellationException;  
import java.util.concurrent.ConcurrentHashMap;  
import java.util.concurrent.ExecutionException;  
import java.util.concurrent.Future;  
import java.util.concurrent.FutureTask;  
import java.util.concurrent.LinkedBlockingQueue;  
import java.util.concurrent.TimeUnit;  
  

/** 
 * @author lixiaohui 
 * @date 2016年10月11日 下午8:11:39 
 *  
 */  
public class Tester {  
      
    static volatile boolean stop = false;  
  
    public static void main(String[] args) throws InterruptedException, IOException {  
        // fixed size 5  
        final MonitorableThreadPoolExecutor pool = new MonitorableThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
  
        pool.addMonitorTask("TimeMonitorTask", newTimeMonitorHandler());  
        // 起一个线程不断地往线程池丢任务  
        Thread t = new Thread(new Runnable() {  
            public void run() {  
                startAddTask(pool);  
            }  
        });  
        t.start();  
          
        // 丢任务丢20 ms  
        Thread.sleep(50);  
        stop = true;  
        t.join();  
        pool.shutdown();  
        // 等线程池任务跑完  
        pool.awaitTermination(100, TimeUnit.SECONDS);  
    }  
  
    private static MonitorHandler newTimeMonitorHandler() {
  
        return new MonitorHandler() {  
            // 任务开始时间记录map, 多线程增删, 需用ConcurrentHashMap  
            Map<Runnable, Long> timeRecords = new ConcurrentHashMap<Runnable, Long>();  
  
            public boolean usable() {  
                return true;  
            }  
              
            public void terminated(int largestPoolSize, long completedTaskCount) {  
                System.out.println(String.format("%s:largestPoolSize=%d, completedTaskCount=%s", time(), largestPoolSize, completedTaskCount));  
            }  
  
            public void before(Thread thread, Runnable runnable) {  
                System.out.println(String.format("%s: before[%s -> %s]", time(), thread, runnable));  
                timeRecords.put(runnable, System.currentTimeMillis());  
            }  
  
            public void after(Runnable runnable, Throwable throwable) {  
                long end = System.currentTimeMillis();  
                Long start = timeRecords.remove(runnable);  
                  
                Object result = null;  
                if (throwable == null && runnable instanceof FutureTask<?>) { // 有返回值的异步任务，不一定是Callable<?>，也有可能是Runnable  
                    try {  
                        result = ((Future<?>) runnable).get();  
                    } catch (InterruptedException e) {  
                        Thread.currentThread().interrupt(); // reset  
                    } catch (ExecutionException e) {  
                        throwable = e;  
                    } catch (CancellationException e) {  
                        throwable = e;  
                    }  
                }  
  
                if (throwable == null) { // 任务正常结束  
                    if (result != null) { // 有返回值的异步任务  
                        System.out.println(String.format("%s: after[%s -> %s], costs %d millisecond, result: %s", time(), Thread.currentThread(), runnable, end - start, result));  
                    } else {  
                        System.out.println(String.format("%s: after[%s -> %s], costs %d millisecond", time(), Thread.currentThread(), runnable, end - start));  
                    }  
                } else {  
                    System.err.println(String.format("%s: after[%s -> %s], costs %d millisecond, exception: %s", time(), Thread.currentThread(), runnable, end - start, throwable));  
                }  
            }  
  
        };  
    }  
  
    // 随机runnable或者callable<?>, 任务随机抛异常  
    private static void startAddTask(MonitorableThreadPoolExecutor pool) {  
        int count = 0;  
        while (!stop) {
                pool.submit(new Callable<Boolean>() {

                    public Boolean call() throws Exception {
                        // 随机抛异常
                        boolean bool = RandomUtils.nextInt(0,2) == 0;
                        // 随机耗时 0~100 ms
                        TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(100,200));
                        if (bool) {
                            throw new RuntimeException("thrown randomly");
                        }
                        return bool;
                    }

                });

//            if (RandomUtils.nextInt(0,2)%2 == 0) {// 丢Callable<?>任务
//                pool.submit(new Callable<Boolean>() {
//
//                    public Boolean call() throws Exception {
//                        // 随机抛异常
//                        boolean bool = RandomUtils.randomBoolean();
//                        // 随机耗时 0~100 ms
//                        Thread.sleep(RandomUtils.randomInt(100));
//                        if (bool) {
//                            throw new RuntimeException("thrown randomly");
//                        }
//                        return bool;
//                    }
//
//                });
//            } else { // 丢Runnable
//                pool.submit(new Runnable() {
//
//                    public void run() {
//                        // 随机耗时 0~100 ms
//                        try {
//                            Thread.sleep(RandomUtils.randomInt(100));
//                        } catch (InterruptedException e) {}
//                        // 随机抛异常
//                        if (RandomUtils.randomBoolean()) {
//                            throw new RuntimeException("thrown randomly");
//                        }
//                    };
//
//                });
//            }
            System.out.println(String.format("%s:submitted %d task", time(), ++count));  
        }  
    }  
  
    private static String time() {  
        return String.valueOf(System.currentTimeMillis());  
    }  
}  