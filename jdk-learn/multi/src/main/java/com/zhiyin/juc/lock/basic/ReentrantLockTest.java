package com.zhiyin.juc.lock.basic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private ReentrantLock lock = new ReentrantLock();
    public void doFirstLock() {
        lock.lock();
        try {
            System.out.println("doFirstLock---" + Thread.currentThread().getId());
            doSecondLock();
        } finally {
            lock.unlock();
        }
    }
    public void doSecondLock() {
        lock.lock();
        try {
            System.out.println("doSecondLock---" + Thread.currentThread().getId());
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    new ReentrantLockTest().doFirstLock();
                }
            }.start();
        }
        TimeUnit.SECONDS.sleep(10);
    }
}