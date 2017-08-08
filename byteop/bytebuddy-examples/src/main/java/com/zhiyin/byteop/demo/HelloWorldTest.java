package com.zhiyin.byteop.demo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by wangqinghui on 2017/2/20.
 */
public class HelloWorldTest {

    @Test
    public void hello() throws Exception {
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)

                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();
        assertThat(dynamicType.newInstance().toString(), is("Hello World!"));
    }
}
