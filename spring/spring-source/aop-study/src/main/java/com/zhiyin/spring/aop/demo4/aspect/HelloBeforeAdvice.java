package com.zhiyin.spring.aop.demo4.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.MethodBeforeAdvice;

@Aspect
public class HelloBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("BEFORE_ADVICE ....");
    }
}
