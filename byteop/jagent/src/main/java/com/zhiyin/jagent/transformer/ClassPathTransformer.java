package com.zhiyin.jagent.transformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import com.zhiyin.jagent.ClazzUtil;
import com.zhiyin.jagent.util.ClassPathUtil;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
public class ClassPathTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        try {
            System.out.println(className + classBeingRedefined.getName());
            System.out.println(ClassPathUtil.getClassAbsPath(classBeingRedefined));
        }catch (Exception e){

        }
        return classfileBuffer;
    }
}