/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.TypePath
 *  com.zhiyin.jagent.asm.tree.AnnotationNode
 *  com.zhiyin.jagent.asm.tree.TypeAnnotationNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.TypePath;
import com.zhiyin.jagent.asm.tree.AnnotationNode;

/*
 * Exception performing whole class analysis ignored.
 */
public class TypeAnnotationNode
extends AnnotationNode {
    public int typeRef;
    public TypePath typePath;
    static /* synthetic */ Class class$org$objectweb$asm$tree$TypeAnnotationNode;

    public TypeAnnotationNode(int n, TypePath typePath, String string) {
        this(327680, n, typePath, string);
        if (this.getClass() != class$org$objectweb$asm$tree$TypeAnnotationNode) {
            throw new IllegalStateException();
        }
    }

    public TypeAnnotationNode(int n, int n2, TypePath typePath, String string) {
        super(n, string);
        this.typeRef = n2;
        this.typePath = typePath;
    }

    static {
        class$org$objectweb$asm$tree$TypeAnnotationNode = TypeAnnotationNode.class$((String)"com.zhiyin.jagent.asm.tree.TypeAnnotationNode");
    }

    static /* synthetic */ Class class$(String string) {
        try {
            return Class.forName(string);
        }
        catch (ClassNotFoundException classNotFoundException) {
            String string2 = classNotFoundException.getMessage();
            throw new NoClassDefFoundError(string2);
        }
    }
}

