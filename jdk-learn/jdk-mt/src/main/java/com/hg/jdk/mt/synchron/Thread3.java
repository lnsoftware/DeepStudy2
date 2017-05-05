package com.hg.jdk.mt.synchron;

public class Thread3 {

	/**
	 * 三、尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，
	 * 其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
	 * @param args
	 */
	public static void main(String[] args) {
		final Thread3 myt2=new Thread3();
		Thread t1=new Thread(
		new Runnable(){
			public void run(){
				myt2.m4t1();
			}
		},"Thread3_t1"
		);
		Thread t2=new Thread(
				new Runnable(){
					public void run(){
						myt2.m4t2();
					}
				},"Thread3_t2"
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
}
