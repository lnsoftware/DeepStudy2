package com.zj.lock.reentrant;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class WriteWork implements Runnable{

	private CountDownLatch latch = null;
	
	private CyclicBarrier barrier = null;
	
	private Map<String, String> maps = null;
	
	private Lock lock = null;
	
	public WriteWork(CountDownLatch latch, CyclicBarrier barrier, Map<String, String> maps, Lock lock) {
		this.latch = latch;
		this.barrier = barrier;
		this.maps = maps;
		this.lock = lock;
	}
	
	public void run() {
		
		try {
			barrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			lock.lock();
			maps.put("1", "2");
			Thread.sleep(100);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		latch.countDown();
	}

}
