package com.zhiyin.jagent.test.runner;

import java.lang.management.ManagementFactory;
import java.util.UUID;

public class DemoServer {

	public static void main(String[] args) throws Exception {

		AttachAgent.attach(DemoServer.class);

		String nameOfRunningVM = ManagementFactory.getRuntimeMXBean().getName();

		System.out.println("server pid:" + nameOfRunningVM);

//		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();//可在jconsole中使用
//		mbs.registerMBean(new Controller(), new ObjectName("MyappMBean:name=controller"));

		while (true) {
			runBody();
		}
	}

	public static void runBody() throws InterruptedException {
		try {
			User u = new User();
			u.setName(UUID.randomUUID().toString());
			System.out.println(u.getName());
			u.hello();
		} catch (Exception e) {
			System.out.println("java.lang.ArithmeticException");
		}
		Thread.sleep(5000);
	}

}
