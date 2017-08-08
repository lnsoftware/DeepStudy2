package com.hg.awesome.thread.demo1;

public class ThreadTest_02 extends Thread{

    private String lock ;
    private String name;
    
    public ThreadTest_02(String name,String lock){
        this.name = name;
        this.lock = lock;
    }
    
    @Override
    public void run() {
        synchronized (lock) {
            for(int i = 0 ; i < 3 ; i++){
                System.out.println(name + " run......");
            }
        }
    }
    
    public static void main(String[] args) {
        String lock  = new String("test");
        for(int i = 0 ; i < 5 ; i++){
            new ThreadTest_02("ThreadTest_" + i,lock).start();
        }
    }
}