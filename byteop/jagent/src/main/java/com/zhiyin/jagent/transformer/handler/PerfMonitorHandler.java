package com.zhiyin.jagent.transformer.handler;

import com.zhiyin.jagent.ClazzUtil;
import javassist.*;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class PerfMonitorHandler extends SubTypeInstrumentationHandler {

    public PerfMonitorHandler() {
        super( Object.class.getName() );
    }

    @Override
    public boolean canTransform(CtClass cc, ClassPool pool) throws NotFoundException {
        return ClazzUtil.classCouldModify(cc);
    }

    protected boolean transform(CtClass cc, ClassPool pool) throws NotFoundException, CannotCompileException, IOException {

        try {

            CtBehavior[] methods = cc.getDeclaredBehaviors();
            for (int i = 0; i < methods.length; i++) {
                if (ClazzUtil.methodCouldModify(methods[i])) {
                    log.info("update class:{}, method:{}",cc.getName(),methods[i].getName());
                    doMethod2(methods[i]);
                }
            }

        } catch (Exception ex) {
            log.error("transform class {} error.",cc.getName(),ex);
        } finally {
            if (cc != null) {
                cc.detach();
            }
        }

        return true;
    }

    private void doMethod2(CtBehavior method) throws CannotCompileException {
        method.addLocalVariable("elapsedTime", CtClass.longType);

        method.insertBefore("elapsedTime = System.currentTimeMillis();");
        String methodInfo = method.getDeclaringClass().getName() + "." + method.getName();
//        method.addLocalVariable("methodInfo",CtClass.charType);
        method.insertAfter("{elapsedTime = System.currentTimeMillis() - elapsedTime;"
                + "System.out.println(\"instrumentation result, " + methodInfo + " executed in ms: \" + elapsedTime);}");
    }



}
