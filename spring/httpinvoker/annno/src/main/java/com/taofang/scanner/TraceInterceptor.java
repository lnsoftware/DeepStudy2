package com.taofang.scanner;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TraceInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {

        try {
            System.out.println("method start");
            return invocation.proceed();
        } catch (Exception e) {
            System.out.println(e);
            return "Exception";
        } finally {
            System.out.println("method end");
        }
    }


}
