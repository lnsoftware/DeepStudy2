package com.zhiyin.jagent.transformer.handler;

import javassist.ClassPool;
import javassist.CtClass;

public interface ClassInstrumentationHandler {
    boolean transformed(CtClass cc, ClassPool pool);
}
