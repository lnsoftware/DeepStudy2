package com.monitor.jmx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentFooBarQix implements Callable<String> {

	private int toFooBar;

	public ConcurrentFooBarQix(int toFooBar) {
		this.toFooBar = toFooBar;
	}

	@Override
	public String call() throws Exception {
		return fooBarQix(toFooBar);
	}

	public String fooBarQix(int anInteger) {
		String result = "";
		if (anInteger % 3 == 0)
			result += "FOO";
		if (anInteger % 5 == 0)
			result += "BAR";
		if (anInteger % 7 == 0)
			result += "QIX";
		String integer = String.valueOf(anInteger);

		for (int i = 0; i < integer.length(); i++) {
			if (integer.charAt(i) == '3')
				result += "FOO";
			if (integer.charAt(i) == '5')
				result += "BAR";
			if (integer.charAt(i) == '7')
				result += "QIX";
		}
		return result.equals("") ? integer : result;
	}

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		List<Callable<String>> callables = new ArrayList<Callable<String>>();

		for (int i = 1; i <= 100; i++) {
			ConcurrentFooBarQix concurrentFooBarQix = new ConcurrentFooBarQix(i);
			callables.add(concurrentFooBarQix);
		}

		ExecutorService executorService = context
				.getBean("executorService",ExecutorService.class);

		for (int i = 0; i < 100 ; i++) {
			executorService.invokeAll(callables);
			TimeUnit.SECONDS.sleep(2);
		}
		List<Future<String>> futures = executorService.invokeAll(callables);
		for (Future<String> future : futures) {
			System.out.println(future.get());
		}
		try {
			Thread.sleep(1000 * 60 * 5);
		} catch (final Throwable t) {
		}

		executorService.shutdown();
	}

}