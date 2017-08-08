package pl.halun.demo.bytebuddy.plain.examples;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

public class RunInterceptor {
	@RuntimeType
	public static void intercept(@This Object obj) {
		System.out.println( "sss"  + Thread.currentThread().getId());
		System.out.println(obj);
	}
}
