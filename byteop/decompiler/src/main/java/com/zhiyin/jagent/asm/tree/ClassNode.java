/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.AnnotationVisitor
 *  com.zhiyin.jagent.asm.Attribute
 *  com.zhiyin.jagent.asm.ClassVisitor
 *  com.zhiyin.jagent.asm.FieldVisitor
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.TypePath
 *  com.zhiyin.jagent.asm.tree.AnnotationNode
 *  com.zhiyin.jagent.asm.tree.ClassNode
 *  com.zhiyin.jagent.asm.tree.FieldNode
 *  com.zhiyin.jagent.asm.tree.InnerClassNode
 *  com.zhiyin.jagent.asm.tree.MethodNode
 *  com.zhiyin.jagent.asm.tree.TypeAnnotationNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.AnnotationVisitor;
import com.zhiyin.jagent.asm.Attribute;
import com.zhiyin.jagent.asm.ClassVisitor;
import com.zhiyin.jagent.asm.FieldVisitor;
import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.TypePath;
import com.zhiyin.jagent.asm.tree.AnnotationNode;
import com.zhiyin.jagent.asm.tree.FieldNode;
import com.zhiyin.jagent.asm.tree.InnerClassNode;
import com.zhiyin.jagent.asm.tree.MethodNode;
import com.zhiyin.jagent.asm.tree.TypeAnnotationNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/*
 * Exception performing whole class analysis ignored.
 */
public class ClassNode
extends ClassVisitor {
    public int version;
    public int access;
    public String name;
    public String signature;
    public String superName;
    public List interfaces = new ArrayList();
    public String sourceFile;
    public String sourceDebug;
    public String outerClass;
    public String outerMethod;
    public String outerMethodDesc;
    public List visibleAnnotations;
    public List invisibleAnnotations;
    public List visibleTypeAnnotations;
    public List invisibleTypeAnnotations;
    public List attrs;
    public List innerClasses = new ArrayList();
    public List fields = new ArrayList();
    public List methods = new ArrayList();
    static /* synthetic */ Class class$org$objectweb$asm$tree$ClassNode;

    public ClassNode() {
        this(327680);
        if (this.getClass() != class$org$objectweb$asm$tree$ClassNode) {
            throw new IllegalStateException();
        }
    }

    public ClassNode(int n) {
        super(n);
    }

    static {
        class$org$objectweb$asm$tree$ClassNode = ClassNode.class$((String)"com.zhiyin.jagent.asm.tree.ClassNode");
    }

    public void accept(ClassVisitor classVisitor) {
        AnnotationNode annotationNode;
        int n;
        String[] arrstring = new String[this.interfaces.size()];
        this.interfaces.toArray(arrstring);
        classVisitor.visit(this.version, this.access, this.name, this.signature, this.superName, arrstring);
        if (this.sourceFile != null || this.sourceDebug != null) {
            classVisitor.visitSource(this.sourceFile, this.sourceDebug);
        }
        if (this.outerClass != null) {
            classVisitor.visitOuterClass(this.outerClass, this.outerMethod, this.outerMethodDesc);
        }
        int n2 = this.visibleAnnotations == null ? 0 : this.visibleAnnotations.size();
        for (n = 0; n < n2; ++n) {
            annotationNode = (AnnotationNode)this.visibleAnnotations.get(n);
            annotationNode.accept(classVisitor.visitAnnotation(annotationNode.desc, true));
        }
        n2 = this.invisibleAnnotations == null ? 0 : this.invisibleAnnotations.size();
        for (n = 0; n < n2; ++n) {
            annotationNode = (AnnotationNode)this.invisibleAnnotations.get(n);
            annotationNode.accept(classVisitor.visitAnnotation(annotationNode.desc, false));
        }
        n2 = this.visibleTypeAnnotations == null ? 0 : this.visibleTypeAnnotations.size();
        for (n = 0; n < n2; ++n) {
            annotationNode = (TypeAnnotationNode)this.visibleTypeAnnotations.get(n);
            annotationNode.accept(classVisitor.visitTypeAnnotation(annotationNode.typeRef, annotationNode.typePath, annotationNode.desc, true));
        }
        n2 = this.invisibleTypeAnnotations == null ? 0 : this.invisibleTypeAnnotations.size();
        for (n = 0; n < n2; ++n) {
            annotationNode = (TypeAnnotationNode)this.invisibleTypeAnnotations.get(n);
            annotationNode.accept(classVisitor.visitTypeAnnotation(annotationNode.typeRef, annotationNode.typePath, annotationNode.desc, false));
        }
        n2 = this.attrs == null ? 0 : this.attrs.size();
        for (n = 0; n < n2; ++n) {
            classVisitor.visitAttribute((Attribute)this.attrs.get(n));
        }
        for (n = 0; n < this.innerClasses.size(); ++n) {
            ((InnerClassNode)this.innerClasses.get(n)).accept(classVisitor);
        }
        for (n = 0; n < this.fields.size(); ++n) {
            ((FieldNode)this.fields.get(n)).accept(classVisitor);
        }
        for (n = 0; n < this.methods.size(); ++n) {
            ((MethodNode)this.methods.get(n)).accept(classVisitor);
        }
        classVisitor.visitEnd();
    }

    public void check(int n) {
        if (n == 262144) {
            FieldNode fieldNode;
            if (this.visibleTypeAnnotations != null && this.visibleTypeAnnotations.size() > 0) {
                throw new RuntimeException();
            }
            if (this.invisibleTypeAnnotations != null && this.invisibleTypeAnnotations.size() > 0) {
                throw new RuntimeException();
            }
            Iterator iterator = this.fields.iterator();
            while (iterator.hasNext()) {
                fieldNode = (FieldNode)iterator.next();
                fieldNode.check(n);
            }
            iterator = this.methods.iterator();
            while (iterator.hasNext()) {
                fieldNode = (MethodNode)iterator.next();
                fieldNode.check(n);
            }
        }
    }

    public void visit(int n, int n2, String string, String string2, String string3, String[] arrstring) {
        this.version = n;
        this.access = n2;
        this.name = string;
        this.signature = string2;
        this.superName = string3;
        if (arrstring != null) {
            this.interfaces.addAll(Arrays.asList(arrstring));
        }
    }

    public void visitOuterClass(String string, String string2, String string3) {
        this.outerClass = string;
        this.outerMethod = string2;
        this.outerMethodDesc = string3;
    }

    public AnnotationVisitor visitAnnotation(String string, boolean bl) {
        AnnotationNode annotationNode = new AnnotationNode(string);
        if (bl) {
            if (this.visibleAnnotations == null) {
                this.visibleAnnotations = new ArrayList(1);
            }
            this.visibleAnnotations.add(annotationNode);
        } else {
            if (this.invisibleAnnotations == null) {
                this.invisibleAnnotations = new ArrayList(1);
            }
            this.invisibleAnnotations.add(annotationNode);
        }
        return annotationNode;
    }

    public void visitSource(String string, String string2) {
        this.sourceFile = string;
        this.sourceDebug = string2;
    }

    public AnnotationVisitor visitTypeAnnotation(int n, TypePath typePath, String string, boolean bl) {
        TypeAnnotationNode typeAnnotationNode = new TypeAnnotationNode(n, typePath, string);
        if (bl) {
            if (this.visibleTypeAnnotations == null) {
                this.visibleTypeAnnotations = new ArrayList(1);
            }
            this.visibleTypeAnnotations.add(typeAnnotationNode);
        } else {
            if (this.invisibleTypeAnnotations == null) {
                this.invisibleTypeAnnotations = new ArrayList(1);
            }
            this.invisibleTypeAnnotations.add(typeAnnotationNode);
        }
        return typeAnnotationNode;
    }

    public void visitAttribute(Attribute attribute) {
        if (this.attrs == null) {
            this.attrs = new ArrayList(1);
        }
        this.attrs.add(attribute);
    }

    public void visitInnerClass(String string, String string2, String string3, int n) {
        InnerClassNode innerClassNode = new InnerClassNode(string, string2, string3, n);
        this.innerClasses.add(innerClassNode);
    }

    public FieldVisitor visitField(int n, String string, String string2, String string3, Object object) {
        FieldNode fieldNode = new FieldNode(n, string, string2, string3, object);
        this.fields.add(fieldNode);
        return fieldNode;
    }

    public MethodVisitor visitMethod(int n, String string, String string2, String string3, String[] arrstring) {
        MethodNode methodNode = new MethodNode(n, string, string2, string3, arrstring);
        this.methods.add(methodNode);
        return methodNode;
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
}

