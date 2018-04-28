/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.AnnotationVisitor
 *  com.zhiyin.jagent.asm.tree.AnnotationNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.AnnotationVisitor;
import java.util.ArrayList;
import java.util.List;

/*
 * Exception performing whole class analysis ignored.
 */
public class AnnotationNode
extends AnnotationVisitor {
    public String desc;
    public List values;
    static /* synthetic */ Class class$org$objectweb$asm$tree$AnnotationNode;

    AnnotationNode(List list) {
        super(327680);
        this.values = list;
    }

    public AnnotationNode(int n, String string) {
        super(n);
        this.desc = string;
    }

    public AnnotationNode(String string) {
        this(327680, string);
        if (this.getClass() != class$org$objectweb$asm$tree$AnnotationNode) {
            throw new IllegalStateException();
        }
    }

    static {
        class$org$objectweb$asm$tree$AnnotationNode = AnnotationNode.class$((String)"com.zhiyin.jagent.asm.tree.AnnotationNode");
    }

    static void accept(AnnotationVisitor annotationVisitor, String string, Object object) {
        if (annotationVisitor != null) {
            if (object instanceof String[]) {
                String[] arrstring = (String[])object;
                annotationVisitor.visitEnum(string, arrstring[0], arrstring[1]);
            } else if (object instanceof AnnotationNode) {
                AnnotationNode annotationNode = (AnnotationNode)object;
                annotationNode.accept(annotationVisitor.visitAnnotation(string, annotationNode.desc));
            } else if (object instanceof List) {
                AnnotationVisitor annotationVisitor2 = annotationVisitor.visitArray(string);
                if (annotationVisitor2 != null) {
                    List list = (List)object;
                    for (int i = 0; i < list.size(); ++i) {
                        AnnotationNode.accept((AnnotationVisitor)annotationVisitor2, (String)null, list.get(i));
                    }
                    annotationVisitor2.visitEnd();
                }
            } else {
                annotationVisitor.visit(string, object);
            }
        }
    }

    public void accept(AnnotationVisitor annotationVisitor) {
        if (annotationVisitor != null) {
            if (this.values != null) {
                for (int i = 0; i < this.values.size(); i += 2) {
                    String string = (String)this.values.get(i);
                    Object e = this.values.get(i + 1);
                    AnnotationNode.accept((AnnotationVisitor)annotationVisitor, (String)string, e);
                }
            }
            annotationVisitor.visitEnd();
        }
    }

    public void check(int n) {
    }

    public void visit(String string, Object object) {
        if (this.values == null) {
            this.values = new ArrayList(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            this.values.add(string);
        }
        this.values.add(object);
    }

    public AnnotationVisitor visitAnnotation(String string, String string2) {
        if (this.values == null) {
            this.values = new ArrayList(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            this.values.add(string);
        }
        AnnotationNode annotationNode = new AnnotationNode(string2);
        this.values.add(annotationNode);
        return annotationNode;
    }

    public void visitEnd() {
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

    public void visitEnum(String string, String string2, String string3) {
        if (this.values == null) {
            this.values = new ArrayList(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            this.values.add(string);
        }
        this.values.add(new String[]{string2, string3});
    }

    public AnnotationVisitor visitArray(String string) {
        if (this.values == null) {
            this.values = new ArrayList(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            this.values.add(string);
        }
        ArrayList arrayList = new ArrayList();
        this.values.add(arrayList);
        return new AnnotationNode(arrayList);
    }
}

