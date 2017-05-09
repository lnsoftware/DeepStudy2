package com.zj.lock.reentrant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 采用读写锁，读多写少问题，性能提升明显
 * @author Administrator
 *
 */
public class ReentrantReadWriteApp {

	private static ReentrantReadWriteLock reentrant = new ReentrantReadWriteLock();
	
	//更好模拟并发，控制所有读写线程到达加锁临界区时，再同时往下执行，抢占锁
	private static CyclicBarrier barrier = new CyclicBarrier(102);
	
	//更好统计耗时，控制所有读写线程都执行完毕，再获取结束时间，计算所有读写线程完成的总耗费时间
	private static CountDownLatch latch = new CountDownLatch(102);
	
	private static Map<String, String> maps = new HashMap<String, String>();
	
	private static WriteLock write = reentrant.writeLock();
	
	private static ReadLock read = reentrant.readLock();
	
	public static void main(String[] args) throws InterruptedException {
		long beginTime = System.currentTimeMillis();
		
		for (int i = 0; i < 100; i++) {
			new Thread(new ReadWork(barrier, read, maps, latch)).start();
		}
		
		for (int i = 0; i < 2; i++) {
			new Thread(new WriteWork(latch, barrier, maps, write)).start();
		}
		
		latch.await();
		
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - beginTime);
		
	}

}
