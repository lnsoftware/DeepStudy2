/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.AnnotationVisitor
 *  com.zhiyin.jagent.asm.Label
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.TypePath
 *  com.zhiyin.jagent.asm.tree.LabelNode
 *  com.zhiyin.jagent.asm.tree.LocalVariableAnnotationNode
 *  com.zhiyin.jagent.asm.tree.TypeAnnotationNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.AnnotationVisitor;
import com.zhiyin.jagent.asm.Label;
import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.TypePath;
import com.zhiyin.jagent.asm.tree.LabelNode;
import com.zhiyin.jagent.asm.tree.TypeAnnotationNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class LocalVariableAnnotationNode
extends TypeAnnotationNode {
    public List start;
    public List end;
    public List index;

    public LocalVariableAnnotationNode(int n, TypePath typePath, LabelNode[] arrlabelNode, LabelNode[] arrlabelNode2, int[] arrn, String string) {
        this(327680, n, typePath, arrlabelNode, arrlabelNode2, arrn, string);
    }

    public LocalVariableAnnotationNode(int n, int n2, TypePath typePath, LabelNode[] arrlabelNode, LabelNode[] arrlabelNode2, int[] arrn, String string) {
        super(n, n2, typePath, string);
        this.start = new ArrayList(arrlabelNode.length);
        this.start.addAll(Arrays.asList(arrlabelNode));
        this.end = new ArrayList(arrlabelNode2.length);
        this.end.addAll(Arrays.asList(arrlabelNode2));
        this.index = new ArrayList(arrn.length);
        int[] arrn2 = arrn;
        int n3 = arrn2.length;
        for (int i = 0; i < n3; ++i) {
            int n4 = arrn2[i];
            this.index.add(new Integer(n4));
        }
    }

    public void accept(MethodVisitor methodVisitor, boolean bl) {
        Label[] arrlabel = new Label[this.start.size()];
        Label[] arrlabel2 = new Label[this.end.size()];
        int[] arrn = new int[this.index.size()];
        for (int i = 0; i < arrlabel.length; ++i) {
            arrlabel[i] = ((LabelNode)this.start.get(i)).getLabel();
            arrlabel2[i] = ((LabelNode)this.end.get(i)).getLabel();
            arrn[i] = (Integer)this.index.get(i);
        }
        this.accept(methodVisitor.visitLocalVariableAnnotation(this.typeRef, this.typePath, arrlabel, arrlabel2, arrn, this.desc, true));
    }
}

