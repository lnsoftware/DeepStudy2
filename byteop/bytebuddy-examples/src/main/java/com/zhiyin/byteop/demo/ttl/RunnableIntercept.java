package com.zhiyin.byteop.demo.ttl;

import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.lang.reflect.Method;

/**
 * Created by wangqinghui on 2017/2/20.
 */
public class RunnableIntercept {

    @RuntimeType
    public static void changeArgs(
            @Super(proxyType = TargetType.class) Object zuper,
            @AllArguments Object[] args,
            @Origin Method method)
            throws Throwable {
        if(args.length==0){
            return;
        }
        String className = args[0].getClass().getName();
        System.out.println(className);
//        args[0] = "This is not your original message.";
        method.invoke(zuper, args);
    }

}
