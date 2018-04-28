/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.MultiANewArrayInsnNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.tree.AbstractInsnNode;
import java.util.Map;

public class MultiANewArrayInsnNode
extends AbstractInsnNode {
    public String desc;
    public int dims;

    public MultiANewArrayInsnNode(String string, int n) {
        super(197);
        this.desc = string;
        this.dims = n;
    }

    public AbstractInsnNode clone(Map map) {
        return new MultiANewArrayInsnNode(this.desc, this.dims).cloneAnnotations((AbstractInsnNode)this);
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitMultiANewArrayInsn(this.desc, this.dims);
        this.acceptAnnotations(methodVisitor);
    }

    public int getType() {
        return 13;
    }
}

