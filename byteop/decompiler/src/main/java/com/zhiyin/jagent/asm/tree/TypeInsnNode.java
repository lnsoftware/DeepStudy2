/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.TypeInsnNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.tree.AbstractInsnNode;
import java.util.Map;

public class TypeInsnNode
extends AbstractInsnNode {
    public String desc;

    public TypeInsnNode(int n, String string) {
        super(n);
        this.desc = string;
    }

    public AbstractInsnNode clone(Map map) {
        return new TypeInsnNode(this.opcode, this.desc).cloneAnnotations((AbstractInsnNode)this);
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitTypeInsn(this.opcode, this.desc);
        this.acceptAnnotations(methodVisitor);
    }

    public int getType() {
        return 3;
    }

    public void setOpcode(int n) {
        this.opcode = n;
    }
}

