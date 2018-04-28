/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.Label
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.LabelNode
 *  com.zhiyin.jagent.asm.tree.LookupSwitchInsnNode
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
public class LookupSwitchInsnNode
extends AbstractInsnNode {
    public LabelNode dflt;
    public List keys;
    public List labels;

    public LookupSwitchInsnNode(LabelNode labelNode, int[] arrn, LabelNode[] arrlabelNode) {
        super(171);
        this.dflt = labelNode;
        this.keys = new ArrayList(arrn == null ? 0 : arrn.length);
        this.labels = new ArrayList(arrlabelNode == null ? 0 : arrlabelNode.length);
        if (arrn != null) {
            for (int i = 0; i < arrn.length; ++i) {
                this.keys.add(new Integer(arrn[i]));
            }
        }
        if (arrlabelNode != null) {
            this.labels.addAll(Arrays.asList(arrlabelNode));
        }
    }

    public AbstractInsnNode clone(Map map) {
        LookupSwitchInsnNode lookupSwitchInsnNode = new LookupSwitchInsnNode(LookupSwitchInsnNode.clone((LabelNode)this.dflt, (Map)map), null, LookupSwitchInsnNode.clone((List)this.labels, (Map)map));
        lookupSwitchInsnNode.keys.addAll(this.keys);
        return lookupSwitchInsnNode.cloneAnnotations((AbstractInsnNode)this);
    }

    public void accept(MethodVisitor methodVisitor) {
        int[] arrn = new int[this.keys.size()];
        for (int i = 0; i < arrn.length; ++i) {
            arrn[i] = (Integer)this.keys.get(i);
        }
        Label[] arrlabel = new Label[this.labels.size()];
        for (int i = 0; i < arrlabel.length; ++i) {
            arrlabel[i] = ((LabelNode)this.labels.get(i)).getLabel();
        }
        methodVisitor.visitLookupSwitchInsn(this.dflt.getLabel(), arrn, arrlabel);
        this.acceptAnnotations(methodVisitor);
    }

    public int getType() {
        return 12;
    }
}

