//package com.zhiyin.asm.demo.method;
//
//
//import com.sun.xml.internal.ws.org.objectweb.asm.MethodAdapter;
//import org.objectweb.asm.MethodVisitor;
//import org.objectweb.asm.*;
//
//public class ModifyMethod extends MethodAdapter {
//
//    public ModifyMethod(MethodVisitor mv, int access, String name, String desc) {
//        super(mv);
//    }
//
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
//}