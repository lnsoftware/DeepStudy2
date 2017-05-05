package com.hg.jdk.mt.lock.d1;


/**
 * lock()方法会对Lock实例对象进行加锁，因此所有对该对象调用lock()方法的线程都会被阻塞，直到该Lock对象的unlock()方法被调用。
 */
public class LockTest implements Runnable{

    Lock lock = null;
    public LockTest(Lock lock) {
        this.lock = lock;
    }

    public void test() {
        for(int i = 0 ; i < 3 ; i++){
            System.out.println(Thread.currentThread().getName() + " run......");
        }
    }

    @Override
    public void run() {
        try {
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //do something
        test();
        lock.unlock();
    }



    public static void main(String[] args) {

        Lock lock = new Lock();



            for(int i = 0 ; i < 5 ; i++){
                new Thread(new LockTest(lock),"t" + i).start();
            }


    }
}