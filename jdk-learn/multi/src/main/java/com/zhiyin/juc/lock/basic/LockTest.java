package com.zhiyin.juc.lock.basic;

public class LockTest {
    public synchronized void lockA() {
        lockB();  //lockA方法中调用lockB方法
    }
    public synchronized void lockB() {
        System.out.println("lock B");
    }

    public static void main(String[] args) {
        new LockTest().lockA();
    }
}
