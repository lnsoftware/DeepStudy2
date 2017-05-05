package com.hg.jdk.mt.synchron.d2;

/**
 * run方法使用的是一个同步方法，而且是static的同步方法，那么这里synchronized锁的又是什么呢？我们知道static超脱于对象之外，它属于类级别的。所以，对象锁就是该静态放发所在的类的Class实例。由于在JVM中，所有被加载的类都有唯一的类对象，在该实例当中就是唯一的 ThreadTest_03.class对象。不管我们创建了该类的多少实例，但是它的类实例仍然是一个！所以对象锁是唯一且共享的。线程同步！！
 */
public class ThreadTest_03 extends Thread{

    public synchronized static void test(){
        for(int i = 0 ; i < 3 ; i++){
            System.out.println(Thread.currentThread().getName() + " run......");
        }
    }
    
    @Override
    public void run() {
        test();
    }

    public static void main(String[] args) {
        for(int i = 0 ; i < 5 ; i++){
            new ThreadTest_03().start();
        }
    }
}