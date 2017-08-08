package com.zhiyin.byteop.demo.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;

public class LoggingInterceptor {

	@RuntimeType
	public static void intercept(@AllArguments Object[] allArguments, @Origin Method method) {

		Logger logger = LoggerFactory.getLogger(method.getDeclaringClass());

		logger.info("Method {} of class {} called", method.getName(), method.getDeclaringClass().getSimpleName());

		Arrays.stream(allArguments).forEach(arg -> logger.info("Method {}, parameter type {}, value={}",
				method.getName(), arg.getClass().getSimpleName(), arg.toString()));
	}

}
