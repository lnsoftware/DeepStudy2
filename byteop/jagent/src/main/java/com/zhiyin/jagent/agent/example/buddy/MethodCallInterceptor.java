package com.zhiyin.jagent.agent.example.buddy;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.jar.asm.commons.Method;


import java.util.concurrent.Callable;

public class MethodCallInterceptor {


	@RuntimeType
	public static void intercept(@SuperCall Callable<?> superMethod ) throws Exception {
//		if (!method.getAnnotation(Secured.class).requiredUser().equals(currentUser)) {
//			throw new IllegalStateException(method + " requires appropriate login”);
//		}
//		if(method.get)

		System.out.println("call");
		   superMethod.call();

	}


//	@RuntimeType
//	public static Object intercept(@SuperCall Callable<?> superCall,
//			@RuntimeType Object value) throws Exception {
//		System.out.println("before");
//
//		Object result = superCall.call();
//
//
//		System.out.println("end");
//		return result;
//	}

//	@RuntimeType
//	public static Object interceptNoParam(@SuperCall Callable<?> superCall)
//			throws Exception {
//		try {
//			return superCall.call();
//		} catch (Exception e) {
//			throw e;
//		}
//	}

//	public static void interceptVoidWithParameter(
//			@SuperCall Callable<?> superCall, @RuntimeType Object value)
//			throws Exception {
//		try {
//			superCall.call();
//		} catch (Exception e) {
//			throw e;
//		}
//	}

//	public static void interceptVoid(@SuperCall Callable<?> superCall)
//			throws Exception {
//		try {
//			superCall.call();
//		} catch (Exception e) {
//			throw e;
//		}
//	}

}
