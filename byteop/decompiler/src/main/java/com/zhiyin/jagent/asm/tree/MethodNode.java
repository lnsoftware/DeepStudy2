/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.AnnotationVisitor
 *  com.zhiyin.jagent.asm.Attribute
 *  com.zhiyin.jagent.asm.ClassVisitor
 *  com.zhiyin.jagent.asm.Handle
 *  com.zhiyin.jagent.asm.Label
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.Type
 *  com.zhiyin.jagent.asm.TypePath
 *  com.zhiyin.jagent.asm.tree.AbstractInsnNode
 *  com.zhiyin.jagent.asm.tree.AnnotationNode
 *  com.zhiyin.jagent.asm.tree.FieldInsnNode
 *  com.zhiyin.jagent.asm.tree.FrameNode
 *  com.zhiyin.jagent.asm.tree.IincInsnNode
 *  com.zhiyin.jagent.asm.tree.InsnList
 *  com.zhiyin.jagent.asm.tree.InsnNode
 *  com.zhiyin.jagent.asm.tree.IntInsnNode
 *  com.zhiyin.jagent.asm.tree.InvokeDynamicInsnNode
 *  com.zhiyin.jagent.asm.tree.JumpInsnNode
 *  com.zhiyin.jagent.asm.tree.LabelNode
 *  com.zhiyin.jagent.asm.tree.LdcInsnNode
 *  com.zhiyin.jagent.asm.tree.LineNumberNode
 *  com.zhiyin.jagent.asm.tree.LocalVariableAnnotationNode
 *  com.zhiyin.jagent.asm.tree.LocalVariableNode
 *  com.zhiyin.jagent.asm.tree.LookupSwitchInsnNode
 *  com.zhiyin.jagent.asm.tree.MethodInsnNode
 *  com.zhiyin.jagent.asm.tree.MethodNode
 *  com.zhiyin.jagent.asm.tree.MethodNode$1
 *  com.zhiyin.jagent.asm.tree.MultiANewArrayInsnNode
 *  com.zhiyin.jagent.asm.tree.ParameterNode
 *  com.zhiyin.jagent.asm.tree.TableSwitchInsnNode
 *  com.zhiyin.jagent.asm.tree.TryCatchBlockNode
 *  com.zhiyin.jagent.asm.tree.TypeAnnotationNode
 *  com.zhiyin.jagent.asm.tree.TypeInsnNode
 *  com.zhiyin.jagent.asm.tree.VarInsnNode
 */
package com.zhiyin.jagent.asm.tree;

import com.zhiyin.jagent.asm.AnnotationVisitor;
import com.zhiyin.jagent.asm.Attribute;
import com.zhiyin.jagent.asm.ClassVisitor;
import com.zhiyin.jagent.asm.Handle;
import com.zhiyin.jagent.asm.Label;
import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.Type;
import com.zhiyin.jagent.asm.TypePath;
import com.zhiyin.jagent.asm.tree.AbstractInsnNode;
import com.zhiyin.jagent.asm.tree.AnnotationNode;
import com.zhiyin.jagent.asm.tree.FieldInsnNode;
import com.zhiyin.jagent.asm.tree.FrameNode;
import com.zhiyin.jagent.asm.tree.IincInsnNode;
import com.zhiyin.jagent.asm.tree.InsnList;
import com.zhiyin.jagent.asm.tree.InsnNode;
import com.zhiyin.jagent.asm.tree.IntInsnNode;
import com.zhiyin.jagent.asm.tree.InvokeDynamicInsnNode;
import com.zhiyin.jagent.asm.tree.JumpInsnNode;
import com.zhiyin.jagent.asm.tree.LabelNode;
import com.zhiyin.jagent.asm.tree.LdcInsnNode;
import com.zhiyin.jagent.asm.tree.LineNumberNode;
import com.zhiyin.jagent.asm.tree.LocalVariableAnnotationNode;
import com.zhiyin.jagent.asm.tree.LocalVariableNode;
import com.zhiyin.jagent.asm.tree.LookupSwitchInsnNode;
import com.zhiyin.jagent.asm.tree.MethodInsnNode;
import com.zhiyin.jagent.asm.tree.MethodNode;
import com.zhiyin.jagent.asm.tree.MultiANewArrayInsnNode;
import com.zhiyin.jagent.asm.tree.ParameterNode;
import com.zhiyin.jagent.asm.tree.TableSwitchInsnNode;
import com.zhiyin.jagent.asm.tree.TryCatchBlockNode;
import com.zhiyin.jagent.asm.tree.TypeAnnotationNode;
import com.zhiyin.jagent.asm.tree.TypeInsnNode;
import com.zhiyin.jagent.asm.tree.VarInsnNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/*
 * Exception performing whole class analysis ignored.
 */
public class MethodNode
extends MethodVisitor {
    public int access;
    public String name;
    public String desc;
    public String signature;
    public List exceptions;
    public List parameters;
    public List visibleAnnotations;
    public List invisibleAnnotations;
    public List visibleTypeAnnotations;
    public List invisibleTypeAnnotations;
    public List attrs;
    public Object annotationDefault;
    public List[] visibleParameterAnnotations;
    public List[] invisibleParameterAnnotations;
    public InsnList instructions;
    public List tryCatchBlocks;
    public int maxStack;
    public int maxLocals;
    public List localVariables;
    public List visibleLocalVariableAnnotations;
    public List invisibleLocalVariableAnnotations;
    private boolean visited;
    static /* synthetic */ Class class$org$objectweb$asm$tree$MethodNode;

    public MethodNode(int n, int n2, String string, String string2, String string3, String[] arrstring) {
        boolean bl;
        super(n);
        this.access = n2;
        this.name = string;
        this.desc = string2;
        this.signature = string3;
        this.exceptions = new ArrayList(arrstring == null ? 0 : arrstring.length);
        boolean bl2 = bl = (n2 & 1024) != 0;
        if (!bl) {
            this.localVariables = new ArrayList(5);
        }
        this.tryCatchBlocks = new ArrayList();
        if (arrstring != null) {
            this.exceptions.addAll(Arrays.asList(arrstring));
        }
        this.instructions = new InsnList();
    }

    public MethodNode(int n, String string, String string2, String string3, String[] arrstring) {
        this(327680, n, string, string2, string3, arrstring);
        if (this.getClass() != class$org$objectweb$asm$tree$MethodNode) {
            throw new IllegalStateException();
        }
    }

    public MethodNode(int n) {
        super(n);
        this.instructions = new InsnList();
    }

    public MethodNode() {
        this(327680);
        if (this.getClass() != class$org$objectweb$asm$tree$MethodNode) {
            throw new IllegalStateException();
        }
    }

    static {
        class$org$objectweb$asm$tree$MethodNode = MethodNode.class$((String)"com.zhiyin.jagent.asm.tree.MethodNode");
    }

    public void accept(ClassVisitor classVisitor) {
        String[] arrstring = new String[this.exceptions.size()];
        this.exceptions.toArray(arrstring);
        MethodVisitor methodVisitor = classVisitor.visitMethod(this.access, this.name, this.desc, this.signature, arrstring);
        if (methodVisitor != null) {
            this.accept(methodVisitor);
        }
    }

    public void accept(MethodVisitor methodVisitor) {
        AnnotationNode annotationNode;
        int n;
        Object object;
        int n2;
        int n3 = this.parameters == null ? 0 : this.parameters.size();
        for (n = 0; n < n3; ++n) {
            object = (ParameterNode)this.parameters.get(n);
            methodVisitor.visitParameter(object.name, object.access);
        }
        if (this.annotationDefault != null) {
            object = methodVisitor.visitAnnotationDefault();
            AnnotationNode.accept((AnnotationVisitor)object, (String)null, (Object)this.annotationDefault);
            if (object != null) {
                object.visitEnd();
            }
        }
        n3 = this.visibleAnnotations == null ? 0 : this.visibleAnnotations.size();
        for (n = 0; n < n3; ++n) {
            object = (AnnotationNode)this.visibleAnnotations.get(n);
            object.accept(methodVisitor.visitAnnotation(object.desc, true));
        }
        n3 = this.invisibleAnnotations == null ? 0 : this.invisibleAnnotations.size();
        for (n = 0; n < n3; ++n) {
            object = (AnnotationNode)this.invisibleAnnotations.get(n);
            object.accept(methodVisitor.visitAnnotation(object.desc, false));
        }
        n3 = this.visibleTypeAnnotations == null ? 0 : this.visibleTypeAnnotations.size();
        for (n = 0; n < n3; ++n) {
            object = (TypeAnnotationNode)this.visibleTypeAnnotations.get(n);
            object.accept(methodVisitor.visitTypeAnnotation(object.typeRef, object.typePath, object.desc, true));
        }
        n3 = this.invisibleTypeAnnotations == null ? 0 : this.invisibleTypeAnnotations.size();
        for (n = 0; n < n3; ++n) {
            object = (TypeAnnotationNode)this.invisibleTypeAnnotations.get(n);
            object.accept(methodVisitor.visitTypeAnnotation(object.typeRef, object.typePath, object.desc, false));
        }
        n3 = this.visibleParameterAnnotations == null ? 0 : this.visibleParameterAnnotations.length;
        for (n = 0; n < n3; ++n) {
            object = this.visibleParameterAnnotations[n];
            if (object == null) continue;
            for (n2 = 0; n2 < object.size(); ++n2) {
                annotationNode = (AnnotationNode)object.get(n2);
                annotationNode.accept(methodVisitor.visitParameterAnnotation(n, annotationNode.desc, true));
            }
        }
        n3 = this.invisibleParameterAnnotations == null ? 0 : this.invisibleParameterAnnotations.length;
        for (n = 0; n < n3; ++n) {
            object = this.invisibleParameterAnnotations[n];
            if (object == null) continue;
            for (n2 = 0; n2 < object.size(); ++n2) {
                annotationNode = (AnnotationNode)object.get(n2);
                annotationNode.accept(methodVisitor.visitParameterAnnotation(n, annotationNode.desc, false));
            }
        }
        if (this.visited) {
            this.instructions.resetLabels();
        }
        n3 = this.attrs == null ? 0 : this.attrs.size();
        for (n = 0; n < n3; ++n) {
            methodVisitor.visitAttribute((Attribute)this.attrs.get(n));
        }
        if (this.instructions.size() > 0) {
            methodVisitor.visitCode();
            n3 = this.tryCatchBlocks == null ? 0 : this.tryCatchBlocks.size();
            for (n = 0; n < n3; ++n) {
                ((TryCatchBlockNode)this.tryCatchBlocks.get(n)).updateIndex(n);
                ((TryCatchBlockNode)this.tryCatchBlocks.get(n)).accept(methodVisitor);
            }
            this.instructions.accept(methodVisitor);
            n3 = this.localVariables == null ? 0 : this.localVariables.size();
            for (n = 0; n < n3; ++n) {
                ((LocalVariableNode)this.localVariables.get(n)).accept(methodVisitor);
            }
            n3 = this.visibleLocalVariableAnnotations == null ? 0 : this.visibleLocalVariableAnnotations.size();
            for (n = 0; n < n3; ++n) {
                ((LocalVariableAnnotationNode)this.visibleLocalVariableAnnotations.get(n)).accept(methodVisitor, true);
            }
            n3 = this.invisibleLocalVariableAnnotations == null ? 0 : this.invisibleLocalVariableAnnotations.size();
            for (n = 0; n < n3; ++n) {
                ((LocalVariableAnnotationNode)this.invisibleLocalVariableAnnotations.get(n)).accept(methodVisitor, false);
            }
            methodVisitor.visitMaxs(this.maxStack, this.maxLocals);
            this.visited = true;
        }
        methodVisitor.visitEnd();
    }

    public void check(int n) {
        if (n == 262144) {
            int n2;
            TryCatchBlockNode tryCatchBlockNode;
            if (this.visibleTypeAnnotations != null && this.visibleTypeAnnotations.size() > 0) {
                throw new RuntimeException();
            }
            if (this.invisibleTypeAnnotations != null && this.invisibleTypeAnnotations.size() > 0) {
                throw new RuntimeException();
            }
            int n3 = this.tryCatchBlocks == null ? 0 : this.tryCatchBlocks.size();
            for (n2 = 0; n2 < n3; ++n2) {
                tryCatchBlockNode = (TryCatchBlockNode)this.tryCatchBlocks.get(n2);
                if (tryCatchBlockNode.visibleTypeAnnotations != null && tryCatchBlockNode.visibleTypeAnnotations.size() > 0) {
                    throw new RuntimeException();
                }
                if (tryCatchBlockNode.invisibleTypeAnnotations == null || tryCatchBlockNode.invisibleTypeAnnotations.size() <= 0) continue;
                throw new RuntimeException();
            }
            for (n2 = 0; n2 < this.instructions.size(); ++n2) {
                boolean bl;
                tryCatchBlockNode = this.instructions.get(n2);
                if (tryCatchBlockNode.visibleTypeAnnotations != null && tryCatchBlockNode.visibleTypeAnnotations.size() > 0) {
                    throw new RuntimeException();
                }
                if (tryCatchBlockNode.invisibleTypeAnnotations != null && tryCatchBlockNode.invisibleTypeAnnotations.size() > 0) {
                    throw new RuntimeException();
                }
                if (!(tryCatchBlockNode instanceof MethodInsnNode) || (bl = ((MethodInsnNode)tryCatchBlockNode).itf) == (tryCatchBlockNode.opcode == 185)) continue;
                throw new RuntimeException();
            }
            if (this.visibleLocalVariableAnnotations != null && this.visibleLocalVariableAnnotations.size() > 0) {
                throw new RuntimeException();
            }
            if (this.invisibleLocalVariableAnnotations != null && this.invisibleLocalVariableAnnotations.size() > 0) {
                throw new RuntimeException();
            }
        }
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

    public void visitEnd() {
    }

    public void visitLdcInsn(Object object) {
        this.instructions.add((AbstractInsnNode)new LdcInsnNode(object));
    }

    public void visitMethodInsn(int n, String string, String string2, String string3) {
        if (this.api >= 327680) {
            super.visitMethodInsn(n, string, string2, string3);
            return;
        }
        this.instructions.add((AbstractInsnNode)new MethodInsnNode(n, string, string2, string3));
    }

    public void visitMethodInsn(int n, String string, String string2, String string3, boolean bl) {
        if (this.api < 327680) {
            super.visitMethodInsn(n, string, string2, string3, bl);
            return;
        }
        this.instructions.add((AbstractInsnNode)new MethodInsnNode(n, string, string2, string3, bl));
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

    public AnnotationVisitor visitInsnAnnotation(int n, TypePath typePath, String string, boolean bl) {
        AbstractInsnNode abstractInsnNode = this.instructions.getLast();
        while (abstractInsnNode.getOpcode() == -1) {
            abstractInsnNode = abstractInsnNode.getPrevious();
        }
        TypeAnnotationNode typeAnnotationNode = new TypeAnnotationNode(n, typePath, string);
        if (bl) {
            if (abstractInsnNode.visibleTypeAnnotations == null) {
                abstractInsnNode.visibleTypeAnnotations = new ArrayList(1);
            }
            abstractInsnNode.visibleTypeAnnotations.add(typeAnnotationNode);
        } else {
            if (abstractInsnNode.invisibleTypeAnnotations == null) {
                abstractInsnNode.invisibleTypeAnnotations = new ArrayList(1);
            }
            abstractInsnNode.invisibleTypeAnnotations.add(typeAnnotationNode);
        }
        return typeAnnotationNode;
    }

    public void visitFieldInsn(int n, String string, String string2, String string3) {
        this.instructions.add((AbstractInsnNode)new FieldInsnNode(n, string, string2, string3));
    }

    public void visitParameter(String string, int n) {
        if (this.parameters == null) {
            this.parameters = new ArrayList(5);
        }
        this.parameters.add(new ParameterNode(string, n));
    }

    public AnnotationVisitor visitAnnotationDefault() {
        return new AnnotationNode((List)new 1(this, 0));
    }

    public void visitCode() {
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String string) {
        this.tryCatchBlocks.add(new TryCatchBlockNode(this.getLabelNode(label), this.getLabelNode(label2), this.getLabelNode(label3), string));
    }

    public void visitLabel(Label label) {
        this.instructions.add((AbstractInsnNode)this.getLabelNode(label));
    }

    public void visitLineNumber(int n, Label label) {
        this.instructions.add((AbstractInsnNode)new LineNumberNode(n, this.getLabelNode(label)));
    }

    public void visitFrame(int n, int n2, Object[] arrobject, int n3, Object[] arrobject2) {
        this.instructions.add((AbstractInsnNode)new FrameNode(n, n2, arrobject == null ? null : this.getLabelNodes(arrobject), n3, arrobject2 == null ? null : this.getLabelNodes(arrobject2)));
    }

    public void visitInsn(int n) {
        this.instructions.add((AbstractInsnNode)new InsnNode(n));
    }

    public void visitVarInsn(int n, int n2) {
        this.instructions.add((AbstractInsnNode)new VarInsnNode(n, n2));
    }

    public void visitJumpInsn(int n, Label label) {
        this.instructions.add((AbstractInsnNode)new JumpInsnNode(n, this.getLabelNode(label)));
    }

    public void visitIincInsn(int n, int n2) {
        this.instructions.add((AbstractInsnNode)new IincInsnNode(n, n2));
    }

    public /* varargs */ void visitTableSwitchInsn(int n, int n2, Label label, Label ... arrlabel) {
        this.instructions.add((AbstractInsnNode)new TableSwitchInsnNode(n, n2, this.getLabelNode(label), this.getLabelNodes(arrlabel)));
    }

    public void visitLookupSwitchInsn(Label label, int[] arrn, Label[] arrlabel) {
        this.instructions.add((AbstractInsnNode)new LookupSwitchInsnNode(this.getLabelNode(label), arrn, this.getLabelNodes(arrlabel)));
    }

    public void visitIntInsn(int n, int n2) {
        this.instructions.add((AbstractInsnNode)new IntInsnNode(n, n2));
    }

    public /* varargs */ void visitInvokeDynamicInsn(String string, String string2, Handle handle, Object ... arrobject) {
        this.instructions.add((AbstractInsnNode)new InvokeDynamicInsnNode(string, string2, handle, arrobject));
    }

    public void visitTypeInsn(int n, String string) {
        this.instructions.add((AbstractInsnNode)new TypeInsnNode(n, string));
    }

    public void visitMultiANewArrayInsn(String string, int n) {
        this.instructions.add((AbstractInsnNode)new MultiANewArrayInsnNode(string, n));
    }

    public void visitLocalVariable(String string, String string2, String string3, Label label, Label label2, int n) {
        this.localVariables.add(new LocalVariableNode(string, string2, string3, this.getLabelNode(label), this.getLabelNode(label2), n));
    }

    public AnnotationVisitor visitLocalVariableAnnotation(int n, TypePath typePath, Label[] arrlabel, Label[] arrlabel2, int[] arrn, String string, boolean bl) {
        LocalVariableAnnotationNode localVariableAnnotationNode = new LocalVariableAnnotationNode(n, typePath, this.getLabelNodes(arrlabel), this.getLabelNodes(arrlabel2), arrn, string);
        if (bl) {
            if (this.visibleLocalVariableAnnotations == null) {
                this.visibleLocalVariableAnnotations = new ArrayList(1);
            }
            this.visibleLocalVariableAnnotations.add(localVariableAnnotationNode);
        } else {
            if (this.invisibleLocalVariableAnnotations == null) {
                this.invisibleLocalVariableAnnotations = new ArrayList(1);
            }
            this.invisibleLocalVariableAnnotations.add(localVariableAnnotationNode);
        }
        return localVariableAnnotationNode;
    }

    public void visitMaxs(int n, int n2) {
        this.maxStack = n;
        this.maxLocals = n2;
    }

    public AnnotationVisitor visitTryCatchAnnotation(int n, TypePath typePath, String string, boolean bl) {
        TryCatchBlockNode tryCatchBlockNode = (TryCatchBlockNode)this.tryCatchBlocks.get((n & 16776960) >> 8);
        TypeAnnotationNode typeAnnotationNode = new TypeAnnotationNode(n, typePath, string);
        if (bl) {
            if (tryCatchBlockNode.visibleTypeAnnotations == null) {
                tryCatchBlockNode.visibleTypeAnnotations = new ArrayList(1);
            }
            tryCatchBlockNode.visibleTypeAnnotations.add(typeAnnotationNode);
        } else {
            if (tryCatchBlockNode.invisibleTypeAnnotations == null) {
                tryCatchBlockNode.invisibleTypeAnnotations = new ArrayList(1);
            }
            tryCatchBlockNode.invisibleTypeAnnotations.add(typeAnnotationNode);
        }
        return typeAnnotationNode;
    }

    public AnnotationVisitor visitParameterAnnotation(int n, String string, boolean bl) {
        AnnotationNode annotationNode = new AnnotationNode(string);
        if (bl) {
            if (this.visibleParameterAnnotations == null) {
                int n2 = Type.getArgumentTypes((String)this.desc).length;
                this.visibleParameterAnnotations = new List[n2];
            }
            if (this.visibleParameterAnnotations[n] == null) {
                this.visibleParameterAnnotations[n] = new ArrayList(1);
            }
            this.visibleParameterAnnotations[n].add(annotationNode);
        } else {
            if (this.invisibleParameterAnnotations == null) {
                int n3 = Type.getArgumentTypes((String)this.desc).length;
                this.invisibleParameterAnnotations = new List[n3];
            }
            if (this.invisibleParameterAnnotations[n] == null) {
                this.invisibleParameterAnnotations[n] = new ArrayList(1);
            }
            this.invisibleParameterAnnotations[n].add(annotationNode);
        }
        return annotationNode;
    }

    private LabelNode[] getLabelNodes(Label[] arrlabel) {
        LabelNode[] arrlabelNode = new LabelNode[arrlabel.length];
        for (int i = 0; i < arrlabel.length; ++i) {
            arrlabelNode[i] = this.getLabelNode(arrlabel[i]);
        }
        return arrlabelNode;
    }

    private Object[] getLabelNodes(Object[] arrobject) {
        Object[] arrobject2 = new Object[arrobject.length];
        for (int i = 0; i < arrobject.length; ++i) {
            Object object = arrobject[i];
            if (object instanceof Label) {
                object = this.getLabelNode((Label)object);
            }
            arrobject2[i] = object;
        }
        return arrobject2;
    }

    protected LabelNode getLabelNode(Label label) {
        if (!(label.info instanceof LabelNode)) {
            label.info = new LabelNode();
        }
        return (LabelNode)label.info;
    }
}

