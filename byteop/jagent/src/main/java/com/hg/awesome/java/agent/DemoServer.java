package com.hg.awesome.java.agent;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.UUID;

public class DemoServer {

	public static void main(String[] args) throws Exception {




		String nameOfRunningVM = ManagementFactory.getRuntimeMXBean().getName();

		System.out.println("server pid:" + nameOfRunningVM);


		while (true) {
			runBody();
		}
	}

	private static final String getProcessID() {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		return ""+Integer.valueOf(runtimeMXBean.getName().split("@")[0])
				.intValue();
	}

	public static void runBody() throws InterruptedException {


		Thread.sleep(5000);
	}



}
