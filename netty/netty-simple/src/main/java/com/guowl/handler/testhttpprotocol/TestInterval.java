package com.guowl.handler.testhttpprotocol;

public class TestInterval {
	public static void main(String[] args) throws Exception{
		while(true){
			HttpClient.main(args);
			Thread.sleep(1000 * 30);
		}
	}
}
