package com.zhiyin.byteop.demo.ttl;

import com.alibaba.ttl.TtlRunnable;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class RunnableAdvice {

    @Advice.OnMethodEnter
    private static void enter(@Advice.Argument(0) Runnable runnable) {

//    @RuntimeType
//    public static void intercept(@Origin Method method,
//                                   @SuperCall Callable<?> callable) {
        System.out.println("rrrr");
        runnable = TtlRunnable.get(runnable);
        System.out.println(runnable.getClass().getName());
    }
 
//    @Advice.OnMethodExit
//    private static void exit(@Advice.Argument(1) HttpServletResponse response) {
//      Callbacks.find(ServletInstrumentation.class).call(2, response);
//    }
  }