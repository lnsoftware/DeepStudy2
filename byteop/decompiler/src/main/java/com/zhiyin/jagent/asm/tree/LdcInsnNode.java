/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.LdcInsnNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.tree.AbstractInsnNode;
import java.util.Map;

public class LdcInsnNode
extends AbstractInsnNode {
    public Object cst;

    public LdcInsnNode(Object object) {
        super(18);
        this.cst = object;
    }

    public AbstractInsnNode clone(Map map) {
        return new LdcInsnNode(this.cst).cloneAnnotations((AbstractInsnNode)this);
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitLdcInsn(this.cst);
        this.acceptAnnotations(methodVisitor);
    }

    public int getType() {
        return 9;
    }
}

