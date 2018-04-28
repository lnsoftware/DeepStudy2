/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.AnnotationVisitor
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.TypePath
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.LabelNode
 *  com.zhiyin.jagent.asm.tree.TypeAnnotationNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.AnnotationVisitor;
import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.TypePath;
import com.zhiyin.jagent.asm.tree.LabelNode;
import com.zhiyin.jagent.asm.tree.TypeAnnotationNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractInsnNode {
    public static final int INSN = 0;
    public static final int INT_INSN = 1;
    public static final int VAR_INSN = 2;
    public static final int TYPE_INSN = 3;
    public static final int FIELD_INSN = 4;
    public static final int METHOD_INSN = 5;
    public static final int INVOKE_DYNAMIC_INSN = 6;
    public static final int JUMP_INSN = 7;
    public static final int LABEL = 8;
    public static final int LDC_INSN = 9;
    public static final int IINC_INSN = 10;
    public static final int TABLESWITCH_INSN = 11;
    public static final int LOOKUPSWITCH_INSN = 12;
    public static final int MULTIANEWARRAY_INSN = 13;
    public static final int FRAME = 14;
    public static final int LINE = 15;
    protected int opcode;
    public List visibleTypeAnnotations;
    public List invisibleTypeAnnotations;
    AbstractInsnNode prev;
    AbstractInsnNode next;
    int index;

    protected AbstractInsnNode(int n) {
        this.opcode = n;
        this.index = -1;
    }

    public abstract AbstractInsnNode clone(Map var1);

    static LabelNode clone(LabelNode labelNode, Map map) {
        return (LabelNode)map.get((Object)labelNode);
    }

    static LabelNode[] clone(List list, Map map) {
        LabelNode[] arrlabelNode = new LabelNode[list.size()];
        for (int i = 0; i < arrlabelNode.length; ++i) {
            arrlabelNode[i] = (LabelNode)map.get(list.get(i));
        }
        return arrlabelNode;
    }

    public abstract void accept(MethodVisitor var1);

    public abstract int getType();

    public int getOpcode() {
        return this.opcode;
    }

    public AbstractInsnNode getPrevious() {
        return this.prev;
    }

    protected final void acceptAnnotations(MethodVisitor methodVisitor) {
        int n;
        TypeAnnotationNode typeAnnotationNode;
        int n2 = this.visibleTypeAnnotations == null ? 0 : this.visibleTypeAnnotations.size();
        for (n = 0; n < n2; ++n) {
            typeAnnotationNode = (TypeAnnotationNode)this.visibleTypeAnnotations.get(n);
            typeAnnotationNode.accept(methodVisitor.visitInsnAnnotation(typeAnnotationNode.typeRef, typeAnnotationNode.typePath, typeAnnotationNode.desc, true));
        }
        n2 = this.invisibleTypeAnnotations == null ? 0 : this.invisibleTypeAnnotations.size();
        for (n = 0; n < n2; ++n) {
            typeAnnotationNode = (TypeAnnotationNode)this.invisibleTypeAnnotations.get(n);
            typeAnnotationNode.accept(methodVisitor.visitInsnAnnotation(typeAnnotationNode.typeRef, typeAnnotationNode.typePath, typeAnnotationNode.desc, false));
        }
    }

    protected final AbstractInsnNode cloneAnnotations(AbstractInsnNode abstractInsnNode) {
        TypeAnnotationNode typeAnnotationNode;
        int n;
        TypeAnnotationNode typeAnnotationNode2;
        if (abstractInsnNode.visibleTypeAnnotations != null) {
            this.visibleTypeAnnotations = new ArrayList<E>();
            for (n = 0; n < abstractInsnNode.visibleTypeAnnotations.size(); ++n) {
                typeAnnotationNode2 = (TypeAnnotationNode)abstractInsnNode.visibleTypeAnnotations.get(n);
                typeAnnotationNode = new TypeAnnotationNode(typeAnnotationNode2.typeRef, typeAnnotationNode2.typePath, typeAnnotationNode2.desc);
                typeAnnotationNode2.accept((AnnotationVisitor)typeAnnotationNode);
                this.visibleTypeAnnotations.add(typeAnnotationNode);
            }
        }
        if (abstractInsnNode.invisibleTypeAnnotations != null) {
            this.invisibleTypeAnnotations = new ArrayList<E>();
            for (n = 0; n < abstractInsnNode.invisibleTypeAnnotations.size(); ++n) {
                typeAnnotationNode2 = (TypeAnnotationNode)abstractInsnNode.invisibleTypeAnnotations.get(n);
                typeAnnotationNode = new TypeAnnotationNode(typeAnnotationNode2.typeRef, typeAnnotationNode2.typePath, typeAnnotationNode2.desc);
                typeAnnotationNode2.accept((AnnotationVisitor)typeAnnotationNode);
                this.invisibleTypeAnnotations.add(typeAnnotationNode);
            }
        }
        return this;
    }

    public AbstractInsnNode getNext() {
        return this.next;
    }
}

