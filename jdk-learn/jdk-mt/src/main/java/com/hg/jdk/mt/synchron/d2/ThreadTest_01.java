package com.hg.jdk.mt.synchron.d2;

/**
 * 一个成员方法加上synchronized关键字后，实际上就是给这个成员方法加上锁，具体点就是以这个成员方法所在的对象本身作为对象锁。
 * 但是在这个实例当中我们一共new了10个ThreadTest对象，那个每个线程都会持有自己线程对象的对象锁，这必定不能产生同步的效果。
 * 所以：如果要对这些线程进行同步，那么这些线程所持有的对象锁应当是共享且唯一的
 */
public class ThreadTest_01 implements Runnable{

    @Override
    public synchronized void run() {
        for(int i = 0 ; i < 3 ; i++){
            System.out.println(Thread.currentThread().getName() + "run......");
        }
    }
    
    public static void main(String[] args) {
        for(int i = 0 ; i < 5 ; i++){
            new Thread(new ThreadTest_01(),"Thread_" + i).start();
        }
    }
}