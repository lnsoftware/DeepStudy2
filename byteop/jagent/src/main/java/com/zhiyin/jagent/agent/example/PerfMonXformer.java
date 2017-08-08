package com.zhiyin.jagent.agent.example;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import com.zhiyin.jagent.ClazzUtil;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
public class PerfMonXformer implements ClassFileTransformer {
    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed = null;
        ClassPool pool = ClassPool.getDefault();
        CtClass cl = null;
        try {
            cl = pool.makeClass(new java.io.ByteArrayInputStream(
                    classfileBuffer));
//            System.out.println(cl.getName());
            if(ClazzUtil.classCouldModify(cl) ) {
                    System.out.println("Transforming " + className);

                    CtBehavior[] methods = cl.getDeclaredBehaviors();
                    for (int i = 0; i < methods.length; i++) {
                        if (methods[i].isEmpty() == false) {
                            doMethod(methods[i]);
                        }
                    }
                    transformed = cl.toBytecode();

            }
        } catch (Exception e) {
            System.err.println("Could not instrument  " + className
                    + ",  exception : " + e.getMessage());
        } finally {
            if (cl != null) {
                cl.detach();
            }
        }
        return transformed;
    }
    private void doMethod(CtBehavior method) throws NotFoundException,
            CannotCompileException {
//         method.insertBefore("long stime = System.nanoTime();");
//         method.insertAfter("System.out.println(\"leave "+method.getName()+" and time:\"+(System.nanoTime()-stime));");
//        method.instrument(new ExprEditor() {
//            public void edit(MethodCall m) throws CannotCompileException {
//                m
//                        .replace("{ long stime = System.nanoTime(); $_ = $proceed($$); System.out.println(\""
//                                + m.getClassName()+"."+m.getMethodName()
//                                + ":\"+(System.nanoTime()-stime));}");
//            }
//        });
    }
}