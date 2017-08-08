package com.hg.awesome.java.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * 对使用到的类打印出所有方法
 * @author wangqinghui
 *http://blog.csdn.net/ykdsg/article/details/14053409
 */
public class GetAllMethodTransformer implements ClassFileTransformer {
	
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
}