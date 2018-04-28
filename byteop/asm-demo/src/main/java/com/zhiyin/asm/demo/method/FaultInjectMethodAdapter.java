package com.zhiyin.asm.demo.method;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class FaultInjectMethodAdapter extends MethodVisitor implements Opcodes {
    private String className;
    private String methodName;
    private String methodDesc;
    private boolean staticFlag;

    public FaultInjectMethodAdapter(MethodVisitor mv, String mClassName, String name,
                                    String methodDescripter, boolean flag) {
        super(ASM5, mv);
        className = mClassName;
        methodName = name;
        methodDesc = methodDescripter;
        staticFlag = flag;
    }


    public void visitCode() {
        int argumentSize = computeStackSize(methodDesc);
//        FaultInfo faultInfo = FaultInfoContainer.getFaultInfo(className, methodName);

        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread",
                "()Ljava/lang/Thread;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getContextClassLoader",
                "()Ljava/lang/ClassLoader;", false);
        mv.visitLdcInsn("com.dianping.rhino.agent.RhinoAgentLauncher");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ClassLoader", "loadClass",
                "(Ljava/lang/String;)Ljava/lang/Class;", false);
        // RhinoAgentLauncher.class
        mv.visitVarInsn(ASTORE, argumentSize + 1);

        mv.visitVarInsn(ALOAD, argumentSize + 1);
        mv.visitLdcInsn("agentClassLoader");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getDeclaredField",
                "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false);
        mv.visitVarInsn(ALOAD, argumentSize + 1);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field", "get",
                "(Ljava/lang/Object;)Ljava/lang/Object;", false);
        mv.visitTypeInsn(CHECKCAST, "java/lang/ClassLoader");
        // the classLoader
        mv.visitVarInsn(ASTORE, argumentSize + 2);

        mv.visitVarInsn(ALOAD, argumentSize + 2);
        mv.visitLdcInsn("com.dianping.rhino.agent.store.FaultInfoContainer");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ClassLoader", "loadClass",
                "(Ljava/lang/String;)Ljava/lang/Class;", false);
        mv.visitVarInsn(ASTORE, argumentSize + 3);
        mv.visitVarInsn(ALOAD, argumentSize + 3);
        mv.visitLdcInsn("getFaultInfo");
        mv.visitInsn(ICONST_2);
        mv.visitTypeInsn(ANEWARRAY, "java/lang/Class");
        mv.visitInsn(DUP);
        mv.visitInsn(ICONST_0);
        mv.visitLdcInsn(Type.getType("Ljava/lang/String;"));
        mv.visitInsn(AASTORE);
        mv.visitInsn(DUP);
        mv.visitInsn(ICONST_1);
        mv.visitLdcInsn(Type.getType("Ljava/lang/String;"));
        mv.visitInsn(AASTORE);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getDeclaredMethod",
                "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
        mv.visitInsn(ACONST_NULL);
        mv.visitInsn(ICONST_2);
        mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
        mv.visitInsn(DUP);
        mv.visitInsn(ICONST_0);
        mv.visitLdcInsn(className);
        mv.visitInsn(AASTORE);
        mv.visitInsn(DUP);
        mv.visitInsn(ICONST_1);
        mv.visitLdcInsn(methodName);
        mv.visitInsn(AASTORE);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Method", "invoke",
                "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", false);
        // faultInfo
        mv.visitVarInsn(ASTORE, argumentSize + 4);

        mv.visitVarInsn(ALOAD, argumentSize + 2);
        mv.visitLdcInsn("com.dianping.rhino.agent.simulator.Injector");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ClassLoader", "loadClass",
                "(Ljava/lang/String;)Ljava/lang/Class;", false);
        mv.visitVarInsn(ASTORE, argumentSize + 5);
        // injector.class
        mv.visitVarInsn(ALOAD, argumentSize + 5);
        mv.visitLdcInsn("getInstance");
        mv.visitInsn(ICONST_0);
        mv.visitTypeInsn(ANEWARRAY, "java/lang/Class");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getDeclaredMethod",
                "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
        mv.visitInsn(ACONST_NULL);
        mv.visitInsn(ICONST_0);
        mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Method", "invoke",
                "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", false);
        // injector
        mv.visitVarInsn(ASTORE, argumentSize + 6);

        mv.visitVarInsn(ALOAD, argumentSize + 2);
        mv.visitLdcInsn("com.dianping.rhino.agent.model.FaultInfo");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ClassLoader", "loadClass",
                "(Ljava/lang/String;)Ljava/lang/Class;", false);
        // fautInfo.class
        mv.visitVarInsn(ASTORE, argumentSize + 7);

        mv.visitVarInsn(ALOAD, argumentSize + 5);
//        if (faultInfo.getFaultType() == FaultType.MockFault) {
//            mv.visitLdcInsn("mock");
//            mv.visitInsn(ICONST_3);
//        } else {
//            mv.visitLdcInsn("inject");
//            mv.visitInsn(ICONST_2);
//        }
//        mv.visitTypeInsn(ANEWARRAY, "java/lang/Class");
//        mv.visitInsn(DUP);
//        mv.visitInsn(ICONST_0);
//        mv.visitLdcInsn(Type.getType("Ljava/lang/String;"));
//        mv.visitInsn(AASTORE);
//        mv.visitInsn(DUP);
//        mv.visitInsn(ICONST_1);
//        mv.visitVarInsn(ALOAD, argumentSize + 7);
//        mv.visitInsn(AASTORE);
//        if (faultInfo.getFaultType() == FaultType.MockFault) {
//            mv.visitInsn(DUP);
//            mv.visitInsn(ICONST_2);
//            mv.visitLdcInsn(Type.getType("Ljava/lang/String;"));
//            mv.visitInsn(AASTORE);
//        }
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getDeclaredMethod",
//                "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
//        mv.visitVarInsn(ALOAD, argumentSize + 6);
//        if (faultInfo.getFaultType() == FaultType.MockFault) {
//            mv.visitInsn(ICONST_3);
//            mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
//            mv.visitInsn(DUP);
//            mv.visitInsn(ICONST_0);
//            mv.visitLdcInsn(className + "." + methodName);
//            mv.visitInsn(AASTORE);
//            mv.visitInsn(DUP);
//            mv.visitInsn(ICONST_1);
//            mv.visitVarInsn(ALOAD, argumentSize + 4);
//            mv.visitInsn(AASTORE);
//            mv.visitInsn(DUP);
//            mv.visitInsn(ICONST_2);
//            mv.visitLdcInsn(methodDesc);
//            mv.visitInsn(AASTORE);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Method", "invoke",
//                    "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", false);
//            this.visitTypeInsn(CHECKCAST, ClassUtils.getName(Type.getReturnType(methodDesc)));
//            this.visitInsn(ARETURN);
//        } else {
//            mv.visitInsn(ICONST_2);
//            mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
//            mv.visitInsn(DUP);
//            mv.visitInsn(ICONST_0);
//            mv.visitLdcInsn(className + "." + methodName);
//            mv.visitInsn(AASTORE);
//            mv.visitInsn(DUP);
//
//            mv.visitInsn(ICONST_1);
//            mv.visitVarInsn(ALOAD, argumentSize + 4);
//            mv.visitInsn(AASTORE);
//
//            Label wapperStartLabel = new Label();
//            Label wapperEndLabel = new Label();
//            Label beforeCatchLabel = new Label();
//            Label enterCatchLabel = new Label();
//            mv.visitTryCatchBlock(wapperStartLabel, beforeCatchLabel, enterCatchLabel,
//                    "java/lang/reflect/InvocationTargetException");
//
//            mv.visitLabel(wapperStartLabel);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Method", "invoke",
//                    "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", false);
//            mv.visitInsn(POP);
//            mv.visitLabel(beforeCatchLabel);
//            mv.visitJumpInsn(GOTO, wapperEndLabel);
//            mv.visitLabel(enterCatchLabel);
//            // mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new
//            // Object[]{"java/lang/reflect/InvocationTargetException"});
//            mv.visitVarInsn(ASTORE, 1);
//            mv.visitVarInsn(ALOAD, 1);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/InvocationTargetException",
//                    "getCause", "()Ljava/lang/Throwable;", false);
//            mv.visitInsn(ATHROW);
//            mv.visitLabel(wapperEndLabel);
//            // mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
//            super.visitCode();
//        }
    }

    private int computeStackSize(String methodDesc) {
        int sum = 0;
        for (Type type : Type.getArgumentTypes(methodDesc)) {
            sum += type.getSize();
        }
        if (!staticFlag) {
            sum += 1;
        }
        return sum;
    }

//    @Override
//    public void visitCode() {
//        mv.visitFieldInsn(Opcodes.GETSTATIC,
//                Type.getInternalName(Person.class), "timer", "J");
//        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
//                "currentTimeMillis", "()J");
//        mv.visitInsn(Opcodes.LSUB);
//        mv.visitFieldInsn(Opcodes.PUTSTATIC,
//                Type.getInternalName(Person.class), "timer", "J");
//    }
//
//    @Override
//    public void visitInsn(int opcode) {
//        if (opcode == Opcodes.RETURN) {
//            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
//                    "Ljava/io/PrintStream;");
//            mv.visitFieldInsn(Opcodes.GETSTATIC,
//                    Type.getInternalName(Person.class), "timer", "J");
//            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
//                    "currentTimeMillis", "()J");
//            mv.visitInsn(Opcodes.LADD);
//            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
//                    "println", "(J)V");
//        }
//        mv.visitInsn(opcode);
//    }

}