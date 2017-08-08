package com.hg.awesome.thread.demo1;

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