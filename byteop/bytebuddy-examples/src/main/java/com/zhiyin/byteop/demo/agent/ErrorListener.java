package com.zhiyin.byteop.demo.agent;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.utility.JavaModule;

@Slf4j
public class ErrorListener implements AgentBuilder.Listener {

    @Override
    public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, DynamicType dynamicType) {
        log.info("agent trans on:{}",typeDescription.getName());
    }

    @Override
    public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
    }

    @Override
    public void onError(String typeName, ClassLoader classLoader, JavaModule module, Throwable throwable) {
        log.info("agent trans error:{}",typeName);
    }

    @Override
    public void onComplete(String typeName, ClassLoader classLoader, JavaModule module) {
//        log.info("agent trans complete:{}",typeName);
    }
}