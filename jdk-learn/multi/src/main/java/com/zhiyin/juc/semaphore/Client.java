package com.zhiyin.juc.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


/**     
 * 类名称：Client    
 * 类描述：    
 *     
 */
public class Client implements Runnable {
	private final int id;
	private Semaphore semaphore;

	public Client(int i, Semaphore semaphore) {
		this.id = i;
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			semaphore.acquire();
			System.out.println("exec");
			semaphore.release();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
