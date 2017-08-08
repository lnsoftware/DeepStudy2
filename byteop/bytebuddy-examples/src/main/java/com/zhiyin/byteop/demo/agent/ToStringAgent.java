package com.zhiyin.byteop.demo.agent;

import net.bytebuddy.agent.builder.AgentBuilder;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.isSubTypeOf;

public class ToStringAgent {
    public static void premain(String arguments, Instrumentation instrumentation) {
        new AgentBuilder.Default()
                .type(isSubTypeOf(Object.class))
                .transform(new ToStringMethodTransformer())
                .installOn(instrumentation);
    }
}