/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.AnnotationVisitor
 *  com.zhiyin.jagent.asm.AnnotationWriter
 *  com.zhiyin.jagent.asm.ByteVector
 *  com.zhiyin.jagent.asm.ClassWriter
 *  com.zhiyin.jagent.asm.Item
 *  com.zhiyin.jagent.asm.Type
 *  com.zhiyin.jagent.asm.TypePath
 */
package com.zhiyin.jagent.asm;

import com.zhiyin.jagent.asm.AnnotationVisitor;
import com.zhiyin.jagent.asm.ByteVector;
import com.zhiyin.jagent.asm.ClassWriter;
import com.zhiyin.jagent.asm.Item;
import com.zhiyin.jagent.asm.Type;
import com.zhiyin.jagent.asm.TypePath;

final class AnnotationWriter
extends AnnotationVisitor {
    private final ClassWriter a;
    private int b;
    private final boolean c;
    private final ByteVector d;
    private final ByteVector e;
    private final int f;
    AnnotationWriter g;
    AnnotationWriter h;

    AnnotationWriter(ClassWriter classWriter, boolean bl, ByteVector byteVector, ByteVector byteVector2, int n) {
        super(327680);
        this.a = classWriter;
        this.c = bl;
        this.d = byteVector;
        this.e = byteVector2;
        this.f = n;
    }

    public void visit(String string, Object object) {
        ++this.b;
        if (this.c) {
            this.d.putShort(this.a.newUTF8(string));
        }
        if (object instanceof String) {
            this.d.b(115, this.a.newUTF8((String)object));
        } else if (object instanceof Byte) {
            this.d.b(66, this.a.a((int)((Byte)object).byteValue()).a);
        } else if (object instanceof Boolean) {
            int n = (Boolean)object != false ? 1 : 0;
            this.d.b(90, this.a.a((int)n).a);
        } else if (object instanceof Character) {
            this.d.b(67, this.a.a((int)((Character)object).charValue()).a);
        } else if (object instanceof Short) {
            this.d.b(83, this.a.a((int)((Short)object).shortValue()).a);
        } else if (object instanceof Type) {
            this.d.b(99, this.a.newUTF8(((Type)object).getDescriptor()));
        } else if (object instanceof byte[]) {
            byte[] arrby = (byte[])object;
            this.d.b(91, arrby.length);
            for (int i = 0; i < arrby.length; ++i) {
                this.d.b(66, this.a.a((int)arrby[i]).a);
            }
        } else if (object instanceof boolean[]) {
            boolean[] arrbl = (boolean[])object;
            this.d.b(91, arrbl.length);
            for (int i = 0; i < arrbl.length; ++i) {
                this.d.b(90, this.a.a((int)(arrbl[i] != false ? 1 : 0)).a);
            }
        } else if (object instanceof short[]) {
            short[] arrs = (short[])object;
            this.d.b(91, arrs.length);
            for (int i = 0; i < arrs.length; ++i) {
                this.d.b(83, this.a.a((int)arrs[i]).a);
            }
        } else if (object instanceof char[]) {
            char[] arrc = (char[])object;
            this.d.b(91, arrc.length);
            for (int i = 0; i < arrc.length; ++i) {
                this.d.b(67, this.a.a((int)arrc[i]).a);
            }
        } else if (object instanceof int[]) {
            int[] arrn = (int[])object;
            this.d.b(91, arrn.length);
            for (int i = 0; i < arrn.length; ++i) {
                this.d.b(73, this.a.a((int)arrn[i]).a);
            }
        } else if (object instanceof long[]) {
            long[] arrl = (long[])object;
            this.d.b(91, arrl.length);
            for (int i = 0; i < arrl.length; ++i) {
                this.d.b(74, this.a.a((long)arrl[i]).a);
            }
        } else if (object instanceof float[]) {
            float[] arrf = (float[])object;
            this.d.b(91, arrf.length);
            for (int i = 0; i < arrf.length; ++i) {
                this.d.b(70, this.a.a((float)arrf[i]).a);
            }
        } else if (object instanceof double[]) {
            double[] arrd = (double[])object;
            this.d.b(91, arrd.length);
            for (int i = 0; i < arrd.length; ++i) {
                this.d.b(68, this.a.a((double)arrd[i]).a);
            }
        } else {
            Item item = this.a.a(object);
            this.d.b((int)".s.IFJDCS".charAt(item.b), item.a);
        }
    }

    public AnnotationVisitor visitAnnotation(String string, String string2) {
        ++this.b;
        if (this.c) {
            this.d.putShort(this.a.newUTF8(string));
        }
        this.d.b(64, this.a.newUTF8(string2)).putShort(0);
        return new AnnotationWriter(this.a, true, this.d, this.d, this.d.b - 2);
    }

    public void visitEnd() {
        if (this.e != null) {
            byte[] arrby = this.e.a;
            arrby[this.f] = (byte)(this.b >>> 8);
            arrby[this.f + 1] = (byte)this.b;
        }
    }

    public void visitEnum(String string, String string2, String string3) {
        ++this.b;
        if (this.c) {
            this.d.putShort(this.a.newUTF8(string));
        }
        this.d.b(101, this.a.newUTF8(string2)).putShort(this.a.newUTF8(string3));
    }

    public AnnotationVisitor visitArray(String string) {
        ++this.b;
        if (this.c) {
            this.d.putShort(this.a.newUTF8(string));
        }
        this.d.b(91, 0);
        return new AnnotationWriter(this.a, false, this.d, this.d, this.d.b - 2);
    }

    static void a(AnnotationWriter[] arrannotationWriter, int n, ByteVector byteVector) {
        int n2;
        int n3 = 1 + 2 * (arrannotationWriter.length - n);
        for (n2 = n; n2 < arrannotationWriter.length; ++n2) {
            n3 += arrannotationWriter[n2] == null ? 0 : arrannotationWriter[n2].a();
        }
        byteVector.putInt(n3).putByte(arrannotationWriter.length - n);
        for (n2 = n; n2 < arrannotationWriter.length; ++n2) {
            AnnotationWriter annotationWriter = arrannotationWriter[n2];
            AnnotationWriter annotationWriter2 = null;
            int n4 = 0;
            while (annotationWriter != null) {
                ++n4;
                annotationWriter.visitEnd();
                annotationWriter.h = annotationWriter2;
                annotationWriter2 = annotationWriter;
                annotationWriter = annotationWriter.g;
            }
            byteVector.putShort(n4);
            annotationWriter = annotationWriter2;
            while (annotationWriter != null) {
                byteVector.putByteArray(annotationWriter.d.a, 0, annotationWriter.d.b);
                annotationWriter = annotationWriter.h;
            }
        }
    }

    static void a(int n, TypePath typePath, ByteVector byteVector) {
        switch (n >>> 24) {
            case 0: 
            case 1: 
            case 22: {
                byteVector.putShort(n >>> 16);
                break;
            }
            case 19: 
            case 20: 
            case 21: {
                byteVector.putByte(n >>> 24);
                break;
            }
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 75: {
                byteVector.putInt(n);
                break;
            }
            default: {
                byteVector.b(n >>> 24, (n & 16776960) >> 8);
            }
        }
        if (typePath == null) {
            byteVector.putByte(0);
        } else {
            int n2 = typePath.a[typePath.b] * 2 + 1;
            byteVector.putByteArray(typePath.a, typePath.b, n2);
        }
    }

    void a(ByteVector byteVector) {
        int n = 0;
        int n2 = 2;
        AnnotationWriter annotationWriter = this;
        AnnotationWriter annotationWriter2 = null;
        while (annotationWriter != null) {
            ++n;
            n2 += annotationWriter.d.b;
            annotationWriter.visitEnd();
            annotationWriter.h = annotationWriter2;
            annotationWriter2 = annotationWriter;
            annotationWriter = annotationWriter.g;
        }
        byteVector.putInt(n2);
        byteVector.putShort(n);
        annotationWriter = annotationWriter2;
        while (annotationWriter != null) {
            byteVector.putByteArray(annotationWriter.d.a, 0, annotationWriter.d.b);
            annotationWriter = annotationWriter.h;
        }
    }

    int a() {
        int n = 0;
        AnnotationWriter annotationWriter = this;
        while (annotationWriter != null) {
            n += annotationWriter.d.b;
            annotationWriter = annotationWriter.g;
        }
        return n;
    }
}

