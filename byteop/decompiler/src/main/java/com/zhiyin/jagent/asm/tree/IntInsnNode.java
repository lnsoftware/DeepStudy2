/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.IntInsnNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.tree.AbstractInsnNode;
import java.util.Map;

public class IntInsnNode
extends AbstractInsnNode {
    public int operand;

    public IntInsnNode(int n, int n2) {
        super(n);
        this.operand = n2;
    }

    public AbstractInsnNode clone(Map map) {
        return new IntInsnNode(this.opcode, this.operand).cloneAnnotations((AbstractInsnNode)this);
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitIntInsn(this.opcode, this.operand);
        this.acceptAnnotations(methodVisitor);
    }

    public int getType() {
        return 1;
    }

    public void setOpcode(int n) {
        this.opcode = n;
    }
}

