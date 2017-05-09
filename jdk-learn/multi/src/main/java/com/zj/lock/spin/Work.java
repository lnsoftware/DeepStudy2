package com.zj.lock.spin;

import java.awt.SplashScreen;

public class Work {

	private static int i = 0;
	
	private static int j = 0;
	
	SpinLock lock = new SpinLock();
	
	TicketLock ticketLock = new TicketLock();
	
	CLHLock clhLock = new CLHLock();
	
	public void work(){
		
		//lock.lock();
		//ticketLock.lock();
		clhLock.lock();
		
		try {
			String name = Thread.currentThread().getName();
			System.out.println(name + " -- in work");
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			j++;
			int m = i * j;
			System.out.println(name + " - " +m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//lock.unLock();
			//ticketLock.unLock();
			clhLock.lock();
		}
	}
	
}
