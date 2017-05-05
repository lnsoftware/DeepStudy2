package com.hg.mybatis;

import java.util.concurrent.*;

/**
 * Created by wangqinghui on 2016/2/16.
 */
public class IdGenj{

//    Log

    // 每毫秒产生数量
    private static final int NodeGenSizePerMs = 1024;
    //
    private static final int ProducerIntervalMs = 100;

    private static final int BatchSize = NodeGenSizePerMs * 2;


    private static final int NUM = 512;
    private static final int MAX = 1000000; // 100W


    protected BlockingQueue<Long> queue = new ArrayBlockingQueue<Long>(
            MAX); // 100W
    private static boolean finished = false;

    private static final ExecutorService pool = Executors.newCachedThreadPool();


    public IdGenj( ) throws InterruptedException {


        // CPU核数*2 个工作者线程
        int threadNum = 2 * Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < threadNum; i++)
            pool.execute(new ConsumerTask());

        pool.execute(new MonitorTask());
//
//        Thread.sleep(10 * 1000);// 10sec
//        System.out.println("going to shutdown server ...");
//        setFinished(true);
//        pool.shutdown();
//
//        pool.awaitTermination(1, TimeUnit.MILLISECONDS);
//
//        System.out.println("colse!");
    }



    public void getData(){

        try {
            Long e = queue.poll(10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        return ;

    }

    private class ProducerTask implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted() && !isFinished()) {

                Long l = 3234L;
                try {
                    queue.put(l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


        // queue monitor，生产者-消费队列监控
    private class MonitorTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted() && !isFinished()) {
                System.out.println("queue.size = " + queue.size());
                try {
                    Thread.sleep(500); // 0.5 second
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    // consumer，消费者
    private class ConsumerTask implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted() && !isFinished()) {
                if (queue.isEmpty()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }

                    continue;
                }
            }
        }

    }


    private static boolean isFinished(){
        return finished;
    }

    private static void setFinished(boolean bool){
        finished = bool;
    }


}
