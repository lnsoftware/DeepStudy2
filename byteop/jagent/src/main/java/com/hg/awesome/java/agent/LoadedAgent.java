package com.hg.awesome.java.agent;


import java.lang.instrument.Instrumentation;

public class LoadedAgent {
    @SuppressWarnings("rawtypes")
    public static void agentmain(String args, Instrumentation inst){
        Class[] classes = inst.getAllLoadedClasses();
        for(Class cls :classes){
            System.out.println(cls.getName());
        }
    }
}