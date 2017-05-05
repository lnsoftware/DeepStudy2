package com.guowl.group.testactivechannel;

public class MultiClient {
	public static void main(String[] args) {
		int threadNum = 10;
		for (int i = 0; i < threadNum; i++) {
			Thread t = new Thread(new HttpClient());
			t.start();
		}
	}
}
