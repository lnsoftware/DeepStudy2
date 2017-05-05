package com.hg.jdk.mt.synchron;

public class Thread2 implements Runnable {

	/**
	 * 然而，当一个线程访问object的一个synchronized(this)同步代码块时，
	 * 另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
	 * @param args
	 */
	public static void main(String[] args) {
		final Thread2 myt2=new Thread2();
		Thread t1=new Thread(
		new Runnable(){
			public void run(){
				myt2.m4t1();
			}
		},"t1"
		);
		Thread t2=new Thread(
				new Runnable(){
					public void run(){
						myt2.m4t2();
					}
				},"t2"
				);
		t1.start();
		t2.start();
	}
	public void m4t1(){
		synchronized(this){
			int i=5;
			while(i-->0){
				System.out.println(Thread.currentThread().getName()+" : "+i);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void m4t2(){
		int i=5;
		while(i-->0){
			System.out.println(Thread.currentThread().getName()+" : "+i);
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		
	}

}
