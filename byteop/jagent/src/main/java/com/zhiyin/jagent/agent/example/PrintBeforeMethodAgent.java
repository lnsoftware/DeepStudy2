package com.jagent.agent.example;//package com.zhiyin.jagent.agent.example;
//
//
//import java.lang.instrument.Instrumentation;
//
//public class PrintBeforeMethodAgent {
//
//    public static void premain(String args, Instrumentation inst){
//        System.out.println("Hi, I'm agent!");
//        inst.addTransformer(new PrintBeforeMethodTransformer());
//        inst.addTransformer(new GetAllMethodTransformer());
//    }
//
//}