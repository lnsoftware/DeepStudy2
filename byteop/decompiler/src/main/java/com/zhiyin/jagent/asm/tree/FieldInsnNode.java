/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.FieldInsnNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.tree.AbstractInsnNode;
import java.util.Map;

public class FieldInsnNode
extends AbstractInsnNode {
    public String owner;
    public String name;
    public String desc;

    public FieldInsnNode(int n, String string, String string2, String string3) {
        super(n);
        this.owner = string;
        this.name = string2;
        this.desc = string3;
    }

    public AbstractInsnNode clone(Map map) {
        return new FieldInsnNode(this.opcode, this.owner, this.name, this.desc).cloneAnnotations((AbstractInsnNode)this);
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitFieldInsn(this.opcode, this.owner, this.name, this.desc);
        this.acceptAnnotations(methodVisitor);
    }

    public int getType() {
        return 4;
    }

    public void setOpcode(int n) {
        this.opcode = n;
    }
}

