package com.zhiyin.rpc.shi.demo.mock;

import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.support.DefaultRemoteInvocationExecutor;
import org.springframework.remoting.support.RemoteInvocation;
import org.springframework.remoting.support.RemoteInvocationExecutor;
import org.springframework.remoting.support.RemoteInvocationResult;

import java.lang.reflect.Method;

/**
 * Created by wangqinghui on 2016/8/31.
 */
public class UmeHttpInvokerServiceExporter extends HttpInvokerServiceExporter {

    private String mockEnable = "true";

    protected RemoteInvocationResult invokeAndCreateResult(RemoteInvocation invocation, Object targetObject) {
        try {
            Object value = invoke(invocation, targetObject);
            return new RemoteInvocationResult(value);
        }
        catch (Throwable ex) {
            try {
                if ("true".equalsIgnoreCase(mockEnable)) {
                    String mockClassName = targetObject.getClass().getName() + "Mock";
                    Class<?> mockClass = Class.forName(mockClassName);
                    Object mockInstance = mockClass.newInstance();

                    Method method = targetObject.getClass().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
                    Object mockResult = method.invoke(mockInstance, invocation.getArguments());

                    return new RemoteInvocationResult(mockResult);
                }
            }catch (Exception e){

            }
            return new RemoteInvocationResult(ex);
        }
    }

}
