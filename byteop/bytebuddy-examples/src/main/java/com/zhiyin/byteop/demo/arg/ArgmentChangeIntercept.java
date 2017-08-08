package com.zhiyin.byteop.demo.arg;

import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.lang.reflect.Method;

/**
 * Created by wangqinghui on 2017/2/20.
 */
public class ArgmentChangeIntercept {

    public static void changeArgs(
            @Super(proxyType = TargetType.class) Object zuper,
            @AllArguments Object[] args,
            @Origin Method method)
            throws Throwable {

        System.out.println(args[0].getClass().getName());
        args[0] = "This is not your original message.";
        method.invoke(zuper, args);
    }

}
