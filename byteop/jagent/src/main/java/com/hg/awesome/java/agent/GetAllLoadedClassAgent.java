package com.hg.awesome.java.agent;


import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.MethodInfo;

/**
 * 获取当前系统中已加载的类。
 * @author wangqinghui
 *
 */
public class GetAllLoadedClassAgent {
    @SuppressWarnings("rawtypes")
    public static void agentmain(String args, Instrumentation inst) throws CannotCompileException{
        Class[] classes = inst.getAllLoadedClasses();
        for(Class cls :classes){
        	
            System.out.println(cls.getName());
            
            String className = cls.getName();
            String compareClass = className.replace('/', '.');
//            if(cls.getName().equals("com.hg.awesome.java.agent.premain.RunServer")){
            	 CtClass clazz = null;
            	    try {
            	        clazz = ClassPool.getDefault().get(compareClass);
            	        CtMethod[] methods = clazz.getMethods();
            	        for(CtMethod method:methods){
//            	            System.out.println(className + ",has method:" + method.getName());
            	            String methodName = method.getName();
            	            if(methodName.equals("runbody")){
            	            	System.out.println("process method："+methodName);
            	            	
            	            	
            	            	   StringBuffer sb=new StringBuffer();  
            	                   sb.append("{System.out.println(\"22222222\");\n")  
            	                    
            	                     .append("System.out.println(\"11111111111\");\n}");  
            	            	method.insertAt(3, " System.out.println(\" agent insert syso!!\"); ");
            	            	MethodInfo tmp = method.getMethodInfo();
//            	            	method.
            	            }
            	          
            	        }
            	    } catch (NotFoundException e) {
            	        e.printStackTrace();
            	    }
            	    
            		
//            }
        }        
       
    }
}

/*

@Override
public byte[] transform(ClassLoader classLoader, String className, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {
    //javassist的包名是用点分割的，需要转换下
    String compareClass = className.replace('/', '.');
    //通过包名获取类文件
    CtClass clazz = null;
    try {
        clazz = ClassPool.getDefault().get(compareClass);
        CtMethod[] methods = clazz.getMethods();
        for(CtMethod method:methods){
            System.out.println(className + ",has method:" + method.getName());
        }
    } catch (NotFoundException e) {
        e.printStackTrace();
    }
    return bytes;
}

*/