package com.hg.jdk.mt.synchron;

public class Thread1 implements Runnable{

	/**
	 * 一、当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。
	 * 另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块
	 * @param args
	 */
	public static void main(String[] args) {
		Thread1 t1=new Thread1();
		Thread ta=new Thread(t1,"A");
		Thread tb=new Thread(t1,"B");
		ta.start();
		tb.start();
	}

	@Override
	public void run() {
		synchronized(this){
			for(int i=0;i<5;i++){
				System.out.println(Thread.currentThread().getName()+" synchronized loop "+i);
			}
		}
	}

}
