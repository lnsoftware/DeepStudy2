package com.zj.lock.spin;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import com.zj.lock.spin.CLHLock.CLHNode;

/**
 * 自旋锁一种形式
 * 公平锁
 * @author Administrator
 *
 */
public class CLHLock {

	
	public static class CLHNode {
		private volatile boolean isLocked = true;
	}
	
	private volatile CLHNode tail;
	
	private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<CLHNode>();
	
	private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATE = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class,
			CLHNode.class, "tail");
	
	public void lock() {
		CLHNode node = new CLHNode();
		LOCAL.set(node);
		CLHNode preNode = UPDATE.getAndSet(this, node);
		if (preNode != null) {
			while (preNode.isLocked) {};
			preNode = null;
			LOCAL.set(node);
		}
	}
	
	public void unLock() {
		CLHNode node = LOCAL.get();
		if (!UPDATE.compareAndSet(this, node, null)) {
			node.isLocked = false;
		}
		node = null;
	}
}
