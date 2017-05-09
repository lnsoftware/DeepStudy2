package com.zhiyin.juc.semaphore;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

public class SimulateFactory {

	public static void main(String[] args) {
		ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(25);
		Semaphore shareSem = new Semaphore(1);
		for (int i = 1; i <= 20; i++) {
			threadPool.execute(new Client(i, shareSem)); //放入线程池执行
		}
		threadPool.shutdown();
	}

}
