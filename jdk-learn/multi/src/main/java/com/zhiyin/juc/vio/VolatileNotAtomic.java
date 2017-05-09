package com.zhiyin.juc.vio;

/**
 * 从上面知道volatile关键字保证了操作的可见性，但是volatile能保证对变量的操作是原子性吗?
 * http://www.cnblogs.com/dolphin0520/p/3920373.html
 * Created by hg on 2017/5/24.
 */

public class VolatileNotAtomic {

    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final VolatileNotAtomic test = new VolatileNotAtomic();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 100; j++)
                        test.increase();
                }
            }.start();
        }

        while (Thread.activeCount() > 1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}