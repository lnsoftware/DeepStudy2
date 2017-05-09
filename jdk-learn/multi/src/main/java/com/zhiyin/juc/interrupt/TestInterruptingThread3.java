package com.zhiyin.juc.interrupt;

/**
 *
 If thread is not in sleeping or waiting state,
 calling the interrupt() method sets the interrupted flag to true that can be used to stop the thread by the java programmer later.
 */
class TestInterruptingThread3 extends Thread {

    public static void main(String args[]) {
        TestInterruptingThread3 t1 = new TestInterruptingThread3();
        t1.start();

        t1.interrupt();

    }

    public void run() {
        for (int i = 1; i <= 5; i++)
            System.out.println(i);
    }
}  