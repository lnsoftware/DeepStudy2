package com.hg.awesome.java.agent;

public class PrintBeforeMethodAgentTest {

	public static void main(String[] args) {
		test();
	}

	public static void test() {
		System.out.println("I'm TestAgent");
	}

}
