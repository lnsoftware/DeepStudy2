package com.zhiyin.juc.lock.basic;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    private AtomicReference<Thread> cas = new AtomicReference<Thread>();
    public void lock() {
        Thread current = Thread.currentThread();
        // 利用CAS
        while (!cas.compareAndSet(null, current)) {
            // DO nothing
        }
    }
    public void unlock() {
        Thread current = Thread.currentThread();
        cas.compareAndSet(current, null);
    }
    public static void main(String[] args) {
        final SpinLock spinLock = new SpinLock();
        System.out.println("invoke lock");
        spinLock.lock();
        System.out.println(" invoke lock success");
        System.out.println("try lock again");
        spinLock.lock();
        System.out.println("try lock success");
    }

}