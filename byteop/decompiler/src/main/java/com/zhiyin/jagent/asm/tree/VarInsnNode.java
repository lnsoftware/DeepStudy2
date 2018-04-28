/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.VarInsnNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.tree.AbstractInsnNode;
import java.util.Map;

public class VarInsnNode
extends AbstractInsnNode {
    public int var;

    public VarInsnNode(int n, int n2) {
        super(n);
        this.var = n2;
    }

    public AbstractInsnNode clone(Map map) {
        return new VarInsnNode(this.opcode, this.var).cloneAnnotations((AbstractInsnNode)this);
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(this.opcode, this.var);
        this.acceptAnnotations(methodVisitor);
    }

    public int getType() {
        return 2;
    }

    public void setOpcode(int n) {
        this.opcode = n;
    }
}

