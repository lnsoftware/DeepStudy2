/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.MethodInsnNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.tree.AbstractInsnNode;
import java.util.Map;

public class MethodInsnNode
extends AbstractInsnNode {
    public String owner;
    public String name;
    public String desc;
    public boolean itf;

    public MethodInsnNode(int n, String string, String string2, String string3) {
        this(n, string, string2, string3, n == 185);
    }

    public MethodInsnNode(int n, String string, String string2, String string3, boolean bl) {
        super(n);
        this.owner = string;
        this.name = string2;
        this.desc = string3;
        this.itf = bl;
    }

    public AbstractInsnNode clone(Map map) {
        return new MethodInsnNode(this.opcode, this.owner, this.name, this.desc, this.itf);
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitMethodInsn(this.opcode, this.owner, this.name, this.desc, this.itf);
        this.acceptAnnotations(methodVisitor);
    }

    public int getType() {
        return 5;
    }

    public void setOpcode(int n) {
        this.opcode = n;
    }
}

