package com.zj.lock.spin;

public class WorkThread extends Thread{

	private Work work;
	
	public WorkThread(String name, Work work) {
		this.work = work;
		setName(name);
	}
	
	@Override
	public void run() {
		work.work();
	}
	
}
