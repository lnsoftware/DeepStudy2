package com.hg.awesome.java.agent;


import java.lang.instrument.Instrumentation;

public class InstrumentAgent {

    public static void premain(String args, Instrumentation inst){
        System.out.println("Hi, I'm premain!");
        inst.addTransformer(new PrintBeforeMethodTransformer());
//        inst.addTransformer(new GetAllMethodTransformer());
    }

    public static void agentmain(String args, Instrumentation inst){
        System.out.println("Hi, I'm agent!");
        inst.addTransformer(new PrintBeforeMethodTransformer());
    }

}