package com.zj.lock.reentrant;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReadWork extends Thread{

	private CyclicBarrier barrier = null;
	
	private CountDownLatch latch = null;
	
	private Lock lock = null;
	
	private Map<String, String> maps = null;
	
	public ReadWork(CyclicBarrier barrier, Lock lock, Map<String, String> maps, CountDownLatch latch) {
		this.barrier = barrier;
		this.lock = lock;
		this.maps = maps;
		this.latch = latch;
	}
	
	@Override
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
			maps.get("1");
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		latch.countDown();
	}
	
}
