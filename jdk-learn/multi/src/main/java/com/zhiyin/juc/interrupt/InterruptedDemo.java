package com.zhiyin.juc.interrupt;

public class InterruptedDemo extends Thread {

    public static void main(String args[]) {

        InterruptedDemo t1 = new InterruptedDemo();
        InterruptedDemo t2 = new InterruptedDemo();

        t1.start();
        t1.interrupt();

        t2.start();

    }

    public void run() {
        for (int i = 1; i <= 2; i++) {
            if (Thread.interrupted()) {
                System.out.println("code for interrupted thread");
            } else {
                System.out.println("code for normal thread");
            }

        }//end of for loop
    }
}
