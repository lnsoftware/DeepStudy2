package com.zhiyin.byteop.demo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

import java.io.File;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by wangqinghui on 2017/2/20.
 */
public class Chage {


    @Test
    public void test() throws Exception {
        DynamicType.Unloaded<Function> build = new ByteBuddy()
                .subclass(Function.class)
                .method(ElementMatchers.named("apply"))
                .intercept(MethodDelegation.to(new GreetingInterceptor()))
                .make();

        build.saveIn(new File(ConstConfig.SavePath));
        Class<? extends java.util.function.Function> dynamicType      = build.load(getClass().getClassLoader())
                .getLoaded();
        assertThat((String) dynamicType.newInstance().apply("Byte Buddy"), is("Hello from Byte Buddy"));
    }

     public class GreetingInterceptor {
        public Object greet(Object argument) {
            return "Hello from " + argument;
        }
    }
}
