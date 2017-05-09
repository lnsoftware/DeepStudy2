package com.zj.lock.reentrant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 采用重入锁，对于读多写少耗费时间
 * @author Administrator
 *
 */
public class ReentrantApp {

	private static ReentrantLock reentrant = new ReentrantLock();
	
	private static CyclicBarrier barrier = new CyclicBarrier(102);
	
	private static CountDownLatch latch = new CountDownLatch(102);
	
	private static Map<String, String> maps = new HashMap<String, String>();
	
	public static void main(String[] args) throws InterruptedException {
		
		long beginTime = System.currentTimeMillis();
		
		for (int i = 0; i < 100; i++) {
			new Thread(new ReadWork(barrier, reentrant, maps, latch)).start();
		}
		
		for (int i = 0; i < 2; i++) {
			new Thread(new WriteWork(latch, barrier, maps, reentrant)).start();
		}
		
		latch.await();
		
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - beginTime);
		
	}

}
