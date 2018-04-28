/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.Label
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.JumpInsnNode
 *  com.zhiyin.jagent.asm.tree.LabelNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.Label;
import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.tree.AbstractInsnNode;
import com.zhiyin.jagent.asm.tree.LabelNode;
import java.util.Map;

/*
 * Exception performing whole class analysis ignored.
 */
public class JumpInsnNode
extends AbstractInsnNode {
    public LabelNode label;

    public JumpInsnNode(int n, LabelNode labelNode) {
        super(n);
        this.label = labelNode;
    }

    public AbstractInsnNode clone(Map map) {
        return new JumpInsnNode(this.opcode, JumpInsnNode.clone((LabelNode)this.label, (Map)map)).cloneAnnotations((AbstractInsnNode)this);
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitJumpInsn(this.opcode, this.label.getLabel());
        this.acceptAnnotations(methodVisitor);
    }

    public int getType() {
        return 7;
    }

    public void setOpcode(int n) {
        this.opcode = n;
    }
}

