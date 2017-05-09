package com.zhiyin.juc.interrupt;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by hg on 2017/6/13.
 */
public class LockSupDemo {

    public static void main(String[] args) throws InterruptedException {
        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread " + Thread.currentThread().getId() + " start!");
                LockSupport.park();
                System.out.println("thread " + Thread.currentThread().getId() + " awake!");
            }
        });

        t.start();
        Thread.sleep(3000);

        // 2. 中断
        t.interrupt();
    }

}
