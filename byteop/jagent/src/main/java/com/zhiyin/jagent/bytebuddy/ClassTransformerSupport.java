package com.zhiyin.jagent.bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * Created by fupan on 16-4-9.
 */
public class ClassTransformerSupport {

    public static void addCode(Instrumentation instrumentation) {
        Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
        System.out.println("all load classes length:" + allLoadedClasses.length);
        System.out.println("object size:" + instrumentation.getObjectSize(new Object()));

        ClassFileTransformer classFileTransformer = new AgentBuilder.Default().type(ElementMatchers.any()).transform(new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader) {
                return builder
                        .method(ElementMatchers.any()).intercept(MethodDelegation.to(DelagateLogging.class)
                                .andThen(SuperMethodCall.INSTANCE));
            }
        }).installOn(instrumentation);
//        new HelloWorld().sayHello();
    }

}