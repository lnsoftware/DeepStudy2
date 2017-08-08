package com.zhiyin.jagent.agent.example;

import com.zhiyin.jagent.ClazzUtil;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

@Slf4j
public class MethodTimeConsumeTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        byte[] byteCode = classfileBuffer;
        CtClass cc = null;
        try {

            cc = ClassPool.getDefault().makeClass(new java.io.ByteArrayInputStream(
                    classfileBuffer));

            if(!className.contains("zhiyin")){
                return null ;
            }
            System.out.println(className);

//            cc = ClassPool.getDefault().get("com.zhiyin.jagent.test.PremainAgentTest");
            if(!ClazzUtil.classCouldModify(cc)){
                return classfileBuffer;
            }
            log.info("process class {}",cc.getName());
            CtBehavior[] methods = cc.getDeclaredBehaviors();
            for (int i = 0; i < methods.length; i++) {
                if (ClazzUtil.methodCouldModify(methods[i])) {
                    log.info("process method {}",methods[i].getName());
                    doMethod2(methods[i]);
                }
            }
            byteCode = cc.toBytecode();

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("transformer",ex);
        } finally {
            if (cc != null) {
                cc.detach();
//                cc.defrost();
            }
        }

        return byteCode;
    }

    private void doMethod2(CtBehavior method) throws CannotCompileException {
        method.addLocalVariable("elapsedTime", CtClass.longType);

        method.insertBefore("elapsedTime = System.currentTimeMillis();");
        String methodInfo = method.getDeclaringClass().getName() + "." + method.getName();
//        method.addLocalVariable("methodInfo",CtClass.charType);
        method.insertAfter("{elapsedTime = System.currentTimeMillis() - elapsedTime;"
                + "System.out.println(\"instrumentation result, " + methodInfo + " executed in ms: \" + elapsedTime);}");
    }

    // 递归分析所有调用
    private void doMethod(CtBehavior method) throws NotFoundException,
            CannotCompileException {
        method.instrument(new ExprEditor() {
            public void edit(MethodCall m) throws CannotCompileException {
                m
                        .replace("{ long stime = System.nanoTime(); $_ = $proceed($$); System.out.println(\""
                                + m.getClassName() + "." + m.getMethodName()
                                + ":\"+(System.nanoTime()-stime));}");
            }
        });
    }
}