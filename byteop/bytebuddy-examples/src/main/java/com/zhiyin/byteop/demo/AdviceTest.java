package com.zhiyin.byteop.demo;

import com.zhiyin.byteop.demo.advice.AdviceInfoAdvice;
import com.zhiyin.byteop.demo.advice.ProfiledAdvice;
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
 * Advice测试
 * Created by wangqinghui on 2017/2/20.
 */
public class AdviceTest {

    @Test
    public void hello() throws Exception {

        DynamicType.Unloaded<Object> build = new ByteBuddy()
                .subclass(Object.class)
                .name(ConstConfig.PackageName + "Demo3")
                .method(named("toString"))
                .intercept(
//                        Advice.to(AdviceInfoAdvice.class)
                        Advice.to(ProfiledAdvice.class)
//                                .appender(Advice.to(ProfilingAdvice.class))
                )
                .make();
        build.saveIn(new File(ConstConfig.SavePath));

        Class<?> dynamicType = build.load(getClass().getClassLoader()).getLoaded();

        String ret = dynamicType.newInstance().toString();
        System.out.println(ret);
//        assertThat(dynamicType.newInstance().toString(), is("Hello World!"));

    }



}
