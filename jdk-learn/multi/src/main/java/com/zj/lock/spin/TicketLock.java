package com.zj.lock.spin;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自旋锁的一种形式
 * 
 * ticket锁，主要解决访问顺序问题，主要问题是在多核cpu上
 * @author Administrator
 *
 */
public class TicketLock {

	private AtomicInteger serviceNum = new AtomicInteger();
	
	private AtomicInteger ticketNum = new AtomicInteger();
	
	private static final ThreadLocal<Integer> LOCK = new ThreadLocal<Integer>();
	
	public void lock() {
		int myTicket = ticketNum.getAndIncrement();
		/*
		 * getAndIncrement()方法，先返回值，然后在加1
		 * System.out.println(myTicket);
		int myTicket1 = ticketNum.getAndIncrement();
		System.out.println(myTicket1);*/
		LOCK.set(myTicket);
		while (myTicket != serviceNum.get()) {};
	}
	
	
	public void unLock() {
		int myTicket = LOCK.get();
		serviceNum.compareAndSet(myTicket, myTicket + 1);
	}
	
	
	public static void main(String[] args) {
		TicketLock lock = new TicketLock();
		lock.lock();
	}
	
}
