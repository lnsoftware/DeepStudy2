package com.zhiyin.byteop.demo.arg;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.Super;
import net.bytebuddy.pool.TypePool;
import org.junit.Test;

import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * Created by wangqinghui on 2017/2/20.
 */
public class ArgChange {


        @Test
        public void test() {

            TypePool typePool = TypePool.Default.ofClassPath();
            new ByteBuddy()

                    .rebase(typePool.describe("com.zhiyin.byteop.demo.arg.SomeClass").resolve(),

                            ClassFileLocator.ForClassLoader.ofClassPath())
                    .method(named("say"))
                    .intercept(
                            MethodDelegation.to(
                                    ArgmentChangeIntercept.class)
                    ).make()
                    .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);
            new SomeClass().say("Hello");
        }





}

