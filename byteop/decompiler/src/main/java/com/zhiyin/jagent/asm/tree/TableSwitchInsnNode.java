/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.Label
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.LabelNode
 *  com.zhiyin.jagent.asm.tree.TableSwitchInsnNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.Label;
import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.tree.AbstractInsnNode;
import com.zhiyin.jagent.asm.tree.LabelNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/*
 * Exception performing whole class analysis ignored.
 */
public class TableSwitchInsnNode
extends AbstractInsnNode {
    public int min;
    public int max;
    public LabelNode dflt;
    public List labels;

    public /* varargs */ TableSwitchInsnNode(int n, int n2, LabelNode labelNode, LabelNode ... arrlabelNode) {
        super(170);
        this.min = n;
        this.max = n2;
        this.dflt = labelNode;
        this.labels = new ArrayList();
        if (arrlabelNode != null) {
            this.labels.addAll(Arrays.asList(arrlabelNode));
        }
    }

    public AbstractInsnNode clone(Map map) {
        return new TableSwitchInsnNode(this.min, this.max, TableSwitchInsnNode.clone((LabelNode)this.dflt, (Map)map), TableSwitchInsnNode.clone((List)this.labels, (Map)map)).cloneAnnotations((AbstractInsnNode)this);
    }

    public void accept(MethodVisitor methodVisitor) {
        Label[] arrlabel = new Label[this.labels.size()];
        for (int i = 0; i < arrlabel.length; ++i) {
            arrlabel[i] = ((LabelNode)this.labels.get(i)).getLabel();
        }
        methodVisitor.visitTableSwitchInsn(this.min, this.max, this.dflt.getLabel(), arrlabel);
        this.acceptAnnotations(methodVisitor);
    }

    public int getType() {
        return 11;
    }
}

