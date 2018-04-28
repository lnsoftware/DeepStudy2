/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.Label
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.LabelNode
 *  com.zhiyin.jagent.asm.tree.LineNumberNode
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
public class LineNumberNode
extends AbstractInsnNode {
    public int line;
    public LabelNode start;

    public LineNumberNode(int n, LabelNode labelNode) {
        super(-1);
        this.line = n;
        this.start = labelNode;
    }

    public AbstractInsnNode clone(Map map) {
        return new LineNumberNode(this.line, LineNumberNode.clone((LabelNode)this.start, (Map)map));
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitLineNumber(this.line, this.start.getLabel());
    }

    public int getType() {
        return 15;
    }
}

