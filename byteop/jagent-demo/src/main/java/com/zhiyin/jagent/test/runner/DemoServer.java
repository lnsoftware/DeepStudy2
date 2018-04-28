package com.zhiyin.jagent.test.runner;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.util.Random;
import java.util.UUID;

@Slf4j
public class DemoServer {

//	static Calculater calculater = new Calculater();
	public static void main(String[] args) throws Exception {
		log.info("test");

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
		Calculater calculater = new Calculater();
		calculater.add(new Random().nextInt(),1);
		try {
			User u = new User();
			u.setName(UUID.randomUUID().toString());
			u.hello();
		} catch (Exception e) {
			System.out.println("java.lang.ArithmeticException");
		}
		Thread.sleep(5000);
	}

}


@Slf4j
@Data
class User {
	private String name;

	public void hello(){
		log.info("hello:"+name);
	}
}
