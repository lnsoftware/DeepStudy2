/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.Attribute
 *  com.zhiyin.jagent.asm.ByteVector
 *  com.zhiyin.jagent.asm.ClassReader
 *  com.zhiyin.jagent.asm.ClassWriter
 *  com.zhiyin.jagent.asm.Label
 */
package com.zhiyin.jagent.asm;

import com.zhiyin.jagent.asm.ByteVector;
import com.zhiyin.jagent.asm.ClassReader;
import com.zhiyin.jagent.asm.ClassWriter;
import com.zhiyin.jagent.asm.Label;

public class Attribute {
    public final String type;
    byte[] b;
    Attribute a;

    protected Attribute(String string) {
        this.type = string;
    }

    protected ByteVector write(ClassWriter classWriter, byte[] arrby, int n, int n2, int n3) {
        ByteVector byteVector = new ByteVector();
        byteVector.a = this.b;
        byteVector.b = this.b.length;
        return byteVector;
    }

    protected Attribute read(ClassReader classReader, int n, int n2, char[] arrc, int n3, Label[] arrlabel) {
        Attribute attribute = new Attribute(this.type);
        attribute.b = new byte[n2];
        System.arraycopy(classReader.b, n, attribute.b, 0, n2);
        return attribute;
    }

    public boolean isCodeAttribute() {
        return false;
    }

    protected Label[] getLabels() {
        return null;
    }

    public boolean isUnknown() {
        return true;
    }

    final void a(ClassWriter classWriter, byte[] arrby, int n, int n2, int n3, ByteVector byteVector) {
        Attribute attribute = this;
        while (attribute != null) {
            ByteVector byteVector2 = attribute.write(classWriter, arrby, n, n2, n3);
            byteVector.putShort(classWriter.newUTF8(attribute.type)).putInt(byteVector2.b);
            byteVector.putByteArray(byteVector2.a, 0, byteVector2.b);
            attribute = attribute.a;
        }
    }

    final int a(ClassWriter classWriter, byte[] arrby, int n, int n2, int n3) {
        Attribute attribute = this;
        int n4 = 0;
        while (attribute != null) {
            classWriter.newUTF8(attribute.type);
            n4 += attribute.write((ClassWriter)classWriter, (byte[])arrby, (int)n, (int)n2, (int)n3).b + 6;
            attribute = attribute.a;
        }
        return n4;
    }

    final int a() {
        int n = 0;
        Attribute attribute = this;
        while (attribute != null) {
            ++n;
            attribute = attribute.a;
        }
        return n;
    }
}

