package com.hg.awesome.java.agent;


import java.lang.instrument.Instrumentation;

public class PrintBeforeMethodAgent {

    public static void premain(String args, Instrumentation inst){
        System.out.println("Hi, I'm agent!");
        inst.addTransformer(new PrintBeforeMethodTransformer());
        inst.addTransformer(new GetAllMethodTransformer());
    }
    
}