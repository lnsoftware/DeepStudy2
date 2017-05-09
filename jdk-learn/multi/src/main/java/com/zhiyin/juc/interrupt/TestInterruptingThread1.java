package com.zhiyin.juc.interrupt;

/**
 * In this example, after interrupting the thread, we are propagating it, so it will stop working.
 * If we don't want to stop the thread, we can handle it where sleep() or wait() method is invoked.
 */
public class TestInterruptingThread1 extends Thread {
    public static void main(String args[]) {
        TestInterruptingThread1 t1 = new TestInterruptingThread1();
        t1.start();
        try {
            t1.interrupt();
        } catch (Exception e) {
            System.out.println("Exception handled " + e);
        }

    }

    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("task");
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted..." + e);
        }

    }
}