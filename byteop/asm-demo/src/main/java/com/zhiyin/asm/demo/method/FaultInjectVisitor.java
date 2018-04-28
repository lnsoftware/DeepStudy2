package com.zhiyin.asm.demo.method;

import org.objectweb.asm.*;

public class FaultInjectVisitor extends ClassVisitor implements Opcodes {
    private String mClassName = null;

    public FaultInjectVisitor(ClassVisitor cv, String mClassName) {
        super(ASM5, cv);
        this.mClassName = mClassName;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                                     String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if ("<clinit>".equals(name) || "<init>".equals(name)) {
            return mv;
        }
            return new FaultInjectMethodAdapter(mv,mClassName, name, descriptor,(access & Opcodes.ACC_STATIC)>0);
    }

}
