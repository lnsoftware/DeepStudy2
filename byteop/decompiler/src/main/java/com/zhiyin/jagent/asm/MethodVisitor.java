/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.AnnotationVisitor
 *  com.zhiyin.jagent.asm.Attribute
 *  com.zhiyin.jagent.asm.Handle
 *  com.zhiyin.jagent.asm.Label
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.TypePath
 */
package com.zhiyin.jagent.asm;

import com.zhiyin.jagent.asm.AnnotationVisitor;
import com.zhiyin.jagent.asm.Attribute;
import com.zhiyin.jagent.asm.Handle;
import com.zhiyin.jagent.asm.Label;
import com.zhiyin.jagent.asm.TypePath;

public abstract class MethodVisitor {
    protected final int api;
    protected MethodVisitor mv;

    public MethodVisitor(int n) {
        this(n, null);
    }

    public MethodVisitor(int n, MethodVisitor methodVisitor) {
        if (n != 262144 && n != 327680) {
            throw new IllegalArgumentException();
        }
        this.api = n;
        this.mv = methodVisitor;
    }

    public AnnotationVisitor visitAnnotation(String string, boolean bl) {
        if (this.mv != null) {
            return this.mv.visitAnnotation(string, bl);
        }
        return null;
    }

    public AnnotationVisitor visitTypeAnnotation(int n, TypePath typePath, String string, boolean bl) {
        if (this.api < 327680) {
            throw new RuntimeException();
        }
        if (this.mv != null) {
            return this.mv.visitTypeAnnotation(n, typePath, string, bl);
        }
        return null;
    }

    public void visitAttribute(Attribute attribute) {
        if (this.mv != null) {
            this.mv.visitAttribute(attribute);
        }
    }

    public void visitEnd() {
        if (this.mv != null) {
            this.mv.visitEnd();
        }
    }

    public void visitLdcInsn(Object object) {
        if (this.mv != null) {
            this.mv.visitLdcInsn(object);
        }
    }

    public void visitMethodInsn(int n, String string, String string2, String string3) {
        if (this.api >= 327680) {
            boolean bl = n == 185;
            this.visitMethodInsn(n, string, string2, string3, bl);
            return;
        }
        if (this.mv != null) {
            this.mv.visitMethodInsn(n, string, string2, string3);
        }
    }

    public void visitMethodInsn(int n, String string, String string2, String string3, boolean bl) {
        if (this.api < 327680) {
            if (bl != (n == 185)) {
                throw new IllegalArgumentException("INVOKESPECIAL/STATIC on interfaces require ASM 5");
            }
            this.visitMethodInsn(n, string, string2, string3);
            return;
        }
        if (this.mv != null) {
            this.mv.visitMethodInsn(n, string, string2, string3, bl);
        }
    }

    public AnnotationVisitor visitInsnAnnotation(int n, TypePath typePath, String string, boolean bl) {
        if (this.api < 327680) {
            throw new RuntimeException();
        }
        if (this.mv != null) {
            return this.mv.visitInsnAnnotation(n, typePath, string, bl);
        }
        return null;
    }

    public void visitFieldInsn(int n, String string, String string2, String string3) {
        if (this.mv != null) {
            this.mv.visitFieldInsn(n, string, string2, string3);
        }
    }

    public void visitParameter(String string, int n) {
        if (this.api < 327680) {
            throw new RuntimeException();
        }
        if (this.mv != null) {
            this.mv.visitParameter(string, n);
        }
    }

    public AnnotationVisitor visitAnnotationDefault() {
        if (this.mv != null) {
            return this.mv.visitAnnotationDefault();
        }
        return null;
    }

    public void visitCode() {
        if (this.mv != null) {
            this.mv.visitCode();
        }
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String string) {
        if (this.mv != null) {
            this.mv.visitTryCatchBlock(label, label2, label3, string);
        }
    }

    public void visitLabel(Label label) {
        if (this.mv != null) {
            this.mv.visitLabel(label);
        }
    }

    public void visitLineNumber(int n, Label label) {
        if (this.mv != null) {
            this.mv.visitLineNumber(n, label);
        }
    }

    public void visitFrame(int n, int n2, Object[] arrobject, int n3, Object[] arrobject2) {
        if (this.mv != null) {
            this.mv.visitFrame(n, n2, arrobject, n3, arrobject2);
        }
    }

    public void visitInsn(int n) {
        if (this.mv != null) {
            this.mv.visitInsn(n);
        }
    }

    public void visitVarInsn(int n, int n2) {
        if (this.mv != null) {
            this.mv.visitVarInsn(n, n2);
        }
    }

    public void visitJumpInsn(int n, Label label) {
        if (this.mv != null) {
            this.mv.visitJumpInsn(n, label);
        }
    }

    public void visitIincInsn(int n, int n2) {
        if (this.mv != null) {
            this.mv.visitIincInsn(n, n2);
        }
    }

    public /* varargs */ void visitTableSwitchInsn(int n, int n2, Label label, Label ... arrlabel) {
        if (this.mv != null) {
            this.mv.visitTableSwitchInsn(n, n2, label, arrlabel);
        }
    }

    public void visitLookupSwitchInsn(Label label, int[] arrn, Label[] arrlabel) {
        if (this.mv != null) {
            this.mv.visitLookupSwitchInsn(label, arrn, arrlabel);
        }
    }

    public void visitIntInsn(int n, int n2) {
        if (this.mv != null) {
            this.mv.visitIntInsn(n, n2);
        }
    }

    public /* varargs */ void visitInvokeDynamicInsn(String string, String string2, Handle handle, Object ... arrobject) {
        if (this.mv != null) {
            this.mv.visitInvokeDynamicInsn(string, string2, handle, arrobject);
        }
    }

    public void visitTypeInsn(int n, String string) {
        if (this.mv != null) {
            this.mv.visitTypeInsn(n, string);
        }
    }

    public void visitMultiANewArrayInsn(String string, int n) {
        if (this.mv != null) {
            this.mv.visitMultiANewArrayInsn(string, n);
        }
    }

    public void visitLocalVariable(String string, String string2, String string3, Label label, Label label2, int n) {
        if (this.mv != null) {
            this.mv.visitLocalVariable(string, string2, string3, label, label2, n);
        }
    }

    public AnnotationVisitor visitLocalVariableAnnotation(int n, TypePath typePath, Label[] arrlabel, Label[] arrlabel2, int[] arrn, String string, boolean bl) {
        if (this.api < 327680) {
            throw new RuntimeException();
        }
        if (this.mv != null) {
            return this.mv.visitLocalVariableAnnotation(n, typePath, arrlabel, arrlabel2, arrn, string, bl);
        }
        return null;
    }

    public void visitMaxs(int n, int n2) {
        if (this.mv != null) {
            this.mv.visitMaxs(n, n2);
        }
    }

    public AnnotationVisitor visitTryCatchAnnotation(int n, TypePath typePath, String string, boolean bl) {
        if (this.api < 327680) {
            throw new RuntimeException();
        }
        if (this.mv != null) {
            return this.mv.visitTryCatchAnnotation(n, typePath, string, bl);
        }
        return null;
    }

    public AnnotationVisitor visitParameterAnnotation(int n, String string, boolean bl) {
        if (this.mv != null) {
            return this.mv.visitParameterAnnotation(n, string, bl);
        }
        return null;
    }
}

