/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.IincInsnNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.tree.AbstractInsnNode;
import java.util.Map;

public class IincInsnNode
extends AbstractInsnNode {
    public int var;
    public int incr;

    public IincInsnNode(int n, int n2) {
        super(132);
        this.var = n;
        this.incr = n2;
    }

    public AbstractInsnNode clone(Map map) {
        return new IincInsnNode(this.var, this.incr).cloneAnnotations((AbstractInsnNode)this);
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitIincInsn(this.var, this.incr);
        this.acceptAnnotations(methodVisitor);
    }

    public int getType() {
        return 10;
    }
}

