/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.AnnotationVisitor
 *  com.zhiyin.jagent.asm.AnnotationWriter
 *  com.zhiyin.jagent.asm.Attribute
 *  com.zhiyin.jagent.asm.ByteVector
 *  com.zhiyin.jagent.asm.ClassWriter
 *  com.zhiyin.jagent.asm.FieldVisitor
 *  com.zhiyin.jagent.asm.FieldWriter
 *  com.zhiyin.jagent.asm.Item
 *  com.zhiyin.jagent.asm.TypePath
 */
package com.zhiyin.jagent.asm;

import com.zhiyin.jagent.asm.AnnotationVisitor;
import com.zhiyin.jagent.asm.AnnotationWriter;
import com.zhiyin.jagent.asm.Attribute;
import com.zhiyin.jagent.asm.ByteVector;
import com.zhiyin.jagent.asm.ClassWriter;
import com.zhiyin.jagent.asm.FieldVisitor;
import com.zhiyin.jagent.asm.Item;
import com.zhiyin.jagent.asm.TypePath;

final class FieldWriter
extends FieldVisitor {
    private final ClassWriter b;
    private final int c;
    private final int d;
    private final int e;
    private int f;
    private int g;
    private AnnotationWriter h;
    private AnnotationWriter i;
    private AnnotationWriter k;
    private AnnotationWriter l;
    private Attribute j;

    FieldWriter(ClassWriter classWriter, int n, String string, String string2, String string3, Object object) {
        super(327680);
        if (classWriter.B == null) {
            classWriter.B = this;
        } else {
            classWriter.C.fv = this;
        }
        classWriter.C = this;
        this.b = classWriter;
        this.c = n;
        this.d = classWriter.newUTF8(string);
        this.e = classWriter.newUTF8(string2);
        if (string3 != null) {
            this.f = classWriter.newUTF8(string3);
        }
        if (object != null) {
            this.g = classWriter.a((Object)object).a;
        }
    }

    public AnnotationVisitor visitAnnotation(String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.b.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.b, true, byteVector, byteVector, 2);
        if (bl) {
            annotationWriter.g = this.h;
            this.h = annotationWriter;
        } else {
            annotationWriter.g = this.i;
            this.i = annotationWriter;
        }
        return annotationWriter;
    }

    public AnnotationVisitor visitTypeAnnotation(int n, TypePath typePath, String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.a((int)n, (TypePath)typePath, (ByteVector)byteVector);
        byteVector.putShort(this.b.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.b, true, byteVector, byteVector, byteVector.b - 2);
        if (bl) {
            annotationWriter.g = this.k;
            this.k = annotationWriter;
        } else {
            annotationWriter.g = this.l;
            this.l = annotationWriter;
        }
        return annotationWriter;
    }

    public void visitAttribute(Attribute attribute) {
        attribute.a = this.j;
        this.j = attribute;
    }

    public void visitEnd() {
    }

    int a() {
        int n = 8;
        if (this.g != 0) {
            this.b.newUTF8("ConstantValue");
            n += 8;
        }
        if ((this.c & 4096) != 0 && ((this.b.b & 65535) < 49 || (this.c & 262144) != 0)) {
            this.b.newUTF8("Synthetic");
            n += 6;
        }
        if ((this.c & 131072) != 0) {
            this.b.newUTF8("Deprecated");
            n += 6;
        }
        if (this.f != 0) {
            this.b.newUTF8("Signature");
            n += 8;
        }
        if (this.h != null) {
            this.b.newUTF8("RuntimeVisibleAnnotations");
            n += 8 + this.h.a();
        }
        if (this.i != null) {
            this.b.newUTF8("RuntimeInvisibleAnnotations");
            n += 8 + this.i.a();
        }
        if (this.k != null) {
            this.b.newUTF8("RuntimeVisibleTypeAnnotations");
            n += 8 + this.k.a();
        }
        if (this.l != null) {
            this.b.newUTF8("RuntimeInvisibleTypeAnnotations");
            n += 8 + this.l.a();
        }
        if (this.j != null) {
            n += this.j.a(this.b, null, 0, -1, -1);
        }
        return n;
    }

    void a(ByteVector byteVector) {
        int n = 64;
        int n2 = 393216 | (this.c & 262144) / 64;
        byteVector.putShort(this.c & ~ n2).putShort(this.d).putShort(this.e);
        int n3 = 0;
        if (this.g != 0) {
            ++n3;
        }
        if ((this.c & 4096) != 0 && ((this.b.b & 65535) < 49 || (this.c & 262144) != 0)) {
            ++n3;
        }
        if ((this.c & 131072) != 0) {
            ++n3;
        }
        if (this.f != 0) {
            ++n3;
        }
        if (this.h != null) {
            ++n3;
        }
        if (this.i != null) {
            ++n3;
        }
        if (this.k != null) {
            ++n3;
        }
        if (this.l != null) {
            ++n3;
        }
        if (this.j != null) {
            n3 += this.j.a();
        }
        byteVector.putShort(n3);
        if (this.g != 0) {
            byteVector.putShort(this.b.newUTF8("ConstantValue"));
            byteVector.putInt(2).putShort(this.g);
        }
        if ((this.c & 4096) != 0 && ((this.b.b & 65535) < 49 || (this.c & 262144) != 0)) {
            byteVector.putShort(this.b.newUTF8("Synthetic")).putInt(0);
        }
        if ((this.c & 131072) != 0) {
            byteVector.putShort(this.b.newUTF8("Deprecated")).putInt(0);
        }
        if (this.f != 0) {
            byteVector.putShort(this.b.newUTF8("Signature"));
            byteVector.putInt(2).putShort(this.f);
        }
        if (this.h != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeVisibleAnnotations"));
            this.h.a(byteVector);
        }
        if (this.i != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeInvisibleAnnotations"));
            this.i.a(byteVector);
        }
        if (this.k != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeVisibleTypeAnnotations"));
            this.k.a(byteVector);
        }
        if (this.l != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeInvisibleTypeAnnotations"));
            this.l.a(byteVector);
        }
        if (this.j != null) {
            this.j.a(this.b, null, 0, -1, -1, byteVector);
        }
    }
}

