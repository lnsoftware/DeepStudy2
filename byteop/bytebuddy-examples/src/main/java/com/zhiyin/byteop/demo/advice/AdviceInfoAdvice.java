package com.zhiyin.byteop.demo.advice;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

/**
 * Created by wangqinghui on 2017/2/20.
 */
public class AdviceInfoAdvice {

    @Advice.OnMethodEnter
    public static long enter(
            @Advice.Origin("#t") String clazzName,

            @Advice.Origin("#m") String methodName,
            @Advice.Origin Method method,
            @Advice.This Object thiz,
            @Advice.Origin("#t.#m") String signature) {
//			TestByteBuddyProfiler.signature = signature;
        System.out.println("clazzName:" + clazzName);
        System.out.println("methodName:" + methodName);
        System.out.println("method.getName():" + method.getName());
        System.out.println("class name:" + method.getDeclaringClass().getSimpleName());
        System.out.println(thiz.getClass().getName());
        System.out.println("signature:"  +signature);
//			return nanoTime();
        return 1;
    }

}
