package com.zhiyin.juc.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class Judge {
	private CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

	/**    
	 * 方法作用：  开始比赛
	 * @return      
	*/
	public void startCompetition() {

		for (int i = 0; i < 10; i++) {
			new Thread(new Runner(cyclicBarrier, i)).start(); //开启十个运动员线程
		}
	}

	public static void main(String[] args) {
		Judge judge=new Judge();
		judge.startCompetition();
	}


}
