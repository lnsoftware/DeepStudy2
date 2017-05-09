package com.zj.lock.spin;

public class Test {

	public static void main(String[] args) {

		Work work = new Work();
		
		for (int i = 0; i < 10; i++) {
			WorkThread workThread = new WorkThread("t-" + i, work);
			workThread.start();
		}
		
	}

}
