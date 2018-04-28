/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.Label
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.LabelNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.Label;
import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.tree.AbstractInsnNode;
import java.util.Map;

public class LabelNode
extends AbstractInsnNode {
    private Label label;

    public LabelNode() {
        super(-1);
    }

    public LabelNode(Label label) {
        super(-1);
        this.label = label;
    }

    public AbstractInsnNode clone(Map map) {
        return (AbstractInsnNode)map.get((Object)this);
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitLabel(this.getLabel());
    }

    public int getType() {
        return 8;
    }

    public Label getLabel() {
        if (this.label == null) {
            this.label = new Label();
        }
        return this.label;
    }

    public void resetLabel() {
        this.label = null;
    }
}

