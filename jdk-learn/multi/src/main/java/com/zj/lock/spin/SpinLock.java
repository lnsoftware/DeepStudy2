package com.zj.lock.spin;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁测试
 * @author Administrator
 *
 */
public class SpinLock {

	private AtomicReference<Thread> spin = new AtomicReference<Thread>();
	
	public void lock() {
		Thread current = Thread.currentThread();
		while (!spin.compareAndSet(null, current)) {
			//System.out.println(current.getName() + ": wait");
		}
	}
	
	public void unLock() {
		Thread current = Thread.currentThread();
		spin.compareAndSet(current, null);
	}
	
}
