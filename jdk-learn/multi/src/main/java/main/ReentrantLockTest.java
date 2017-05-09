package main;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	private ArrayList<Integer> arrayList = new ArrayList<>();

	public static void main(String[] args) {
		final ReentrantLockTest test = new ReentrantLockTest();

		/*
		 * 锁乱了 Lock lock = new ReentrantLock()在方法中,是局部变量,所以获得的锁不是同一把锁
		 */
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					try {
						test.insert(Thread.currentThread());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}

	public void insert(Thread thread) throws InterruptedException {
		Lock lock = new ReentrantLock();// 注意這個地方
		lock.lock();
		try {
			System.out.println(thread.getName() + "得到了锁");
			for (int i = 0; i < 5; i++) {
				arrayList.add(i);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println(thread.getName() + "释放了锁");
			Thread.sleep(1000);
			lock.unlock();
		}
	}

}
