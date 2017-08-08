package com.zhiyin.byteop.demo;

import com.zhiyin.byteop.demo.interceptor.LoggingInterceptor;
import com.zhiyin.byteop.demo.interceptor.TimingInterceptor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import org.junit.Test;

import java.io.File;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * Created by wangqinghui on 2017/2/20.
 */
public class InterceptTest {

    @Test
    public void hello() throws Exception {

        DynamicType.Unloaded<Object> build = new ByteBuddy()
                .subclass(Object.class)
                .name("Demo3")
                .method(named("toString"))

                .intercept(
                        MethodDelegation.to(TimingInterceptor.class)
                                .andThen(SuperMethodCall.INSTANCE)
                )
                .make();
        build.saveIn(new File(ConstConfig.SavePath));

        Class<?> dynamicType = build.load(getClass().getClassLoader()).getLoaded();

        String ret = dynamicType.newInstance().toString();
        System.out.println(ret);
//        assertThat(dynamicType.newInstance().toString(), is("Hello World!"));

    }

    @Test
    public void cal() throws Exception {

        DynamicType.Unloaded<Calculater> build = new ByteBuddy()
                .subclass(Calculater.class)
                .name("Calculater2")
                .method(named("div"))
                .intercept(
                        MethodDelegation.to(TimingInterceptor.class)
                                .andThen(
                                        MethodDelegation.to(LoggingInterceptor.class)
                                                .andThen(SuperMethodCall.INSTANCE)
                                )
                )
                .make();
        build.saveIn(new File(ConstConfig.SavePath));

        Class<Calculater> dynamicType = (Class<Calculater>) build.load(getClass().getClassLoader()).getLoaded();

        String ret = dynamicType.newInstance().toString();
        System.out.println(ret);
        int div = dynamicType.newInstance().div(2, 1);
        System.out.println(div);
//        assertThat(dynamicType.newInstance().toString(), is("Hello World!"));

    }


}
