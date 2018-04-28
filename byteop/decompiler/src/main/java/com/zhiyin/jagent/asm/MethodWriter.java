/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.AnnotationVisitor
 *  com.zhiyin.jagent.asm.AnnotationWriter
 *  com.zhiyin.jagent.asm.Attribute
 *  com.zhiyin.jagent.asm.ByteVector
 *  com.zhiyin.jagent.asm.ClassReader
 *  com.zhiyin.jagent.asm.ClassWriter
 *  com.zhiyin.jagent.asm.Edge
 *  com.zhiyin.jagent.asm.Frame
 *  com.zhiyin.jagent.asm.Handle
 *  com.zhiyin.jagent.asm.Handler
 *  com.zhiyin.jagent.asm.Item
 *  com.zhiyin.jagent.asm.Label
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.MethodWriter
 *  com.zhiyin.jagent.asm.Type
 *  com.zhiyin.jagent.asm.TypePath
 */
package com.zhiyin.jagent.asm;

import com.zhiyin.jagent.asm.AnnotationVisitor;
import com.zhiyin.jagent.asm.AnnotationWriter;
import com.zhiyin.jagent.asm.Attribute;
import com.zhiyin.jagent.asm.ByteVector;
import com.zhiyin.jagent.asm.ClassReader;
import com.zhiyin.jagent.asm.ClassWriter;
import com.zhiyin.jagent.asm.Edge;
import com.zhiyin.jagent.asm.Frame;
import com.zhiyin.jagent.asm.Handle;
import com.zhiyin.jagent.asm.Handler;
import com.zhiyin.jagent.asm.Item;
import com.zhiyin.jagent.asm.Label;
import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.Type;
import com.zhiyin.jagent.asm.TypePath;

/*
 * Exception performing whole class analysis ignored.
 */
class MethodWriter
extends MethodVisitor {
    final ClassWriter b;
    private int c;
    private final int d;
    private final int e;
    private final String f;
    String g;
    int h;
    int i;
    int j;
    int[] k;
    private ByteVector l;
    private AnnotationWriter m;
    private AnnotationWriter n;
    private AnnotationWriter U;
    private AnnotationWriter V;
    private AnnotationWriter[] o;
    private AnnotationWriter[] p;
    private int S;
    private Attribute q;
    private ByteVector r = new ByteVector();
    private int s;
    private int t;
    private int T;
    private int u;
    private ByteVector v;
    private int w;
    private int[] x;
    private int[] z;
    private int A;
    private Handler B;
    private Handler C;
    private int Z;
    private ByteVector $;
    private int D;
    private ByteVector E;
    private int F;
    private ByteVector G;
    private int H;
    private ByteVector I;
    private int Y;
    private AnnotationWriter W;
    private AnnotationWriter X;
    private Attribute J;
    private boolean K;
    private int L;
    private final int M;
    private Label N;
    private Label O;
    private Label P;
    private int Q;
    private int R;

    MethodWriter(ClassWriter classWriter, int n, String string, String string2, String string3, String[] arrstring, boolean bl, boolean bl2) {
        int n2;
        super(327680);
        if (classWriter.D == null) {
            classWriter.D = this;
        } else {
            classWriter.E.mv = this;
        }
        classWriter.E = this;
        this.b = classWriter;
        this.c = n;
        if ("<init>".equals(string)) {
            this.c |= 524288;
        }
        this.d = classWriter.newUTF8(string);
        this.e = classWriter.newUTF8(string2);
        this.f = string2;
        this.g = string3;
        if (arrstring != null && arrstring.length > 0) {
            this.j = arrstring.length;
            this.k = new int[this.j];
            for (n2 = 0; n2 < this.j; ++n2) {
                this.k[n2] = classWriter.newClass(arrstring[n2]);
            }
        }
        int n3 = bl2 ? 0 : (this.M = bl ? 1 : 2);
        if (bl || bl2) {
            n2 = Type.getArgumentsAndReturnSizes((String)this.f) >> 2;
            if ((n & 8) != 0) {
                --n2;
            }
            this.t = n2;
            this.T = n2;
            this.N = new Label();
            this.N.a |= 8;
            this.visitLabel(this.N);
        }
    }

    static int c(byte[] arrby, int n) {
        return (arrby[n] & 255) << 8 | arrby[n + 1] & 255;
    }

    private void c() {
        int n = this.z[1];
        int n2 = this.z[2];
        if ((this.b.b & 65535) < 50) {
            this.v.putShort(this.z[0]).putShort(n);
            this.a(3, 3 + n);
            this.v.putShort(n2);
            this.a(3 + n, 3 + n + n2);
            return;
        }
        int n3 = this.x[1];
        int n4 = 255;
        int n5 = 0;
        int n6 = this.u == 0 ? this.z[0] : this.z[0] - this.x[0] - 1;
        if (n2 == 0) {
            n5 = n - n3;
            switch (n5) {
                case -3: 
                case -2: 
                case -1: {
                    n4 = 248;
                    n3 = n;
                    break;
                }
                case 0: {
                    n4 = n6 < 64 ? 0 : 251;
                    break;
                }
                case 1: 
                case 2: 
                case 3: {
                    n4 = 252;
                }
            }
        } else if (n == n3 && n2 == 1) {
            int n7 = n4 = n6 < 63 ? 64 : 247;
        }
        if (n4 != 255) {
            int n8 = 3;
            for (int i = 0; i < n3; ++i) {
                if (this.z[n8] != this.x[n8]) {
                    n4 = 255;
                    break;
                }
                ++n8;
            }
        }
        switch (n4) {
            case 0: {
                this.v.putByte(n6);
                break;
            }
            case 64: {
                this.v.putByte(64 + n6);
                this.a(3 + n, 4 + n);
                break;
            }
            case 247: {
                this.v.putByte(247).putShort(n6);
                this.a(3 + n, 4 + n);
                break;
            }
            case 251: {
                this.v.putByte(251).putShort(n6);
                break;
            }
            case 248: {
                this.v.putByte(251 + n5).putShort(n6);
                break;
            }
            case 252: {
                this.v.putByte(251 + n5).putShort(n6);
                this.a(3 + n3, 3 + n);
                break;
            }
            default: {
                this.v.putByte(255).putShort(n6).putShort(n);
                this.a(3, 3 + n);
                this.v.putShort(n2);
                this.a(3 + n, 3 + n + n2);
            }
        }
    }

    public AnnotationVisitor visitAnnotation(String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.b.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.b, true, byteVector, byteVector, 2);
        if (bl) {
            annotationWriter.g = this.m;
            this.m = annotationWriter;
        } else {
            annotationWriter.g = this.n;
            this.n = annotationWriter;
        }
        return annotationWriter;
    }

    public AnnotationVisitor visitTypeAnnotation(int n, TypePath typePath, String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.a((int)n, (TypePath)typePath, (ByteVector)byteVector);
        byteVector.putShort(this.b.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.b, true, byteVector, byteVector, byteVector.b - 2);
        if (bl) {
            annotationWriter.g = this.U;
            this.U = annotationWriter;
        } else {
            annotationWriter.g = this.V;
            this.V = annotationWriter;
        }
        return annotationWriter;
    }

    public void visitAttribute(Attribute attribute) {
        if (attribute.isCodeAttribute()) {
            attribute.a = this.J;
            this.J = attribute;
        } else {
            attribute.a = this.q;
            this.q = attribute;
        }
    }

    public void visitEnd() {
    }

    public void visitLdcInsn(Object object) {
        int n;
        this.Y = this.r.b;
        Item item = this.b.a(object);
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(18, 0, this.b, item);
            } else {
                n = item.b == 5 || item.b == 6 ? this.Q + 2 : this.Q + 1;
                if (n > this.R) {
                    this.R = n;
                }
                this.Q = n;
            }
        }
        n = item.a;
        if (item.b == 5 || item.b == 6) {
            this.r.b(20, n);
        } else if (n >= 256) {
            this.r.b(19, n);
        } else {
            this.r.a(18, n);
        }
    }

    public void visitMethodInsn(int n, String string, String string2, String string3, boolean bl) {
        this.Y = this.r.b;
        Item item = this.b.a(string, string2, string3, bl);
        int n2 = item.c;
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(n, 0, this.b, item);
            } else {
                if (n2 == 0) {
                    item.c = n2 = Type.getArgumentsAndReturnSizes((String)string3);
                }
                int n3 = n == 184 ? this.Q - (n2 >> 2) + (n2 & 3) + 1 : this.Q - (n2 >> 2) + (n2 & 3);
                if (n3 > this.R) {
                    this.R = n3;
                }
                this.Q = n3;
            }
        }
        if (n == 185) {
            if (n2 == 0) {
                item.c = n2 = Type.getArgumentsAndReturnSizes((String)string3);
            }
            this.r.b(185, item.a).a(n2 >> 2, 0);
        } else {
            this.r.b(n, item.a);
        }
    }

    public AnnotationVisitor visitInsnAnnotation(int n, TypePath typePath, String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        n = n & -16776961 | this.Y << 8;
        AnnotationWriter.a((int)n, (TypePath)typePath, (ByteVector)byteVector);
        byteVector.putShort(this.b.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.b, true, byteVector, byteVector, byteVector.b - 2);
        if (bl) {
            annotationWriter.g = this.W;
            this.W = annotationWriter;
        } else {
            annotationWriter.g = this.X;
            this.X = annotationWriter;
        }
        return annotationWriter;
    }

    public void visitFieldInsn(int n, String string, String string2, String string3) {
        this.Y = this.r.b;
        Item item = this.b.a(string, string2, string3);
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(n, 0, this.b, item);
            } else {
                int n2;
                char c = string3.charAt(0);
                switch (n) {
                    case 178: {
                        n2 = this.Q + (c == 'D' || c == 'J' ? 2 : 1);
                        break;
                    }
                    case 179: {
                        n2 = this.Q + (c == 'D' || c == 'J' ? -2 : -1);
                        break;
                    }
                    case 180: {
                        n2 = this.Q + (c == 'D' || c == 'J' ? 1 : 0);
                        break;
                    }
                    default: {
                        n2 = this.Q + (c == 'D' || c == 'J' ? -3 : -2);
                    }
                }
                if (n2 > this.R) {
                    this.R = n2;
                }
                this.Q = n2;
            }
        }
        this.r.b(n, item.a);
    }

    public void visitParameter(String string, int n) {
        if (this.$ == null) {
            this.$ = new ByteVector();
        }
        ++this.Z;
        this.$.putShort(string == null ? 0 : this.b.newUTF8(string)).putShort(n);
    }

    public AnnotationVisitor visitAnnotationDefault() {
        this.l = new ByteVector();
        return new AnnotationWriter(this.b, false, this.l, null, 0);
    }

    public void visitCode() {
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String string) {
        ++this.A;
        Handler handler = new Handler();
        handler.a = label;
        handler.b = label2;
        handler.c = label3;
        handler.d = string;
        int n = handler.e = string != null ? this.b.newClass(string) : 0;
        if (this.C == null) {
            this.B = handler;
        } else {
            this.C.f = handler;
        }
        this.C = handler;
    }

    public void visitLabel(Label label) {
        this.K |= label.a(this, this.r.b, this.r.a);
        if ((label.a & 1) != 0) {
            return;
        }
        if (this.M == 0) {
            if (this.P != null) {
                if (label.c == this.P.c) {
                    this.P.a |= label.a & 16;
                    label.h = this.P.h;
                    return;
                }
                this.a(0, label);
            }
            this.P = label;
            if (label.h == null) {
                label.h = new Frame();
                label.h.b = label;
            }
            if (this.O != null) {
                if (label.c == this.O.c) {
                    this.O.a |= label.a & 16;
                    label.h = this.O.h;
                    this.P = this.O;
                    return;
                }
                this.O.i = label;
            }
            this.O = label;
        } else if (this.M == 1) {
            if (this.P != null) {
                this.P.g = this.R;
                this.a(this.Q, label);
            }
            this.P = label;
            this.Q = 0;
            this.R = 0;
            if (this.O != null) {
                this.O.i = label;
            }
            this.O = label;
        }
    }

    public void visitLineNumber(int n, Label label) {
        if (this.I == null) {
            this.I = new ByteVector();
        }
        ++this.H;
        this.I.putShort(label.c);
        this.I.putShort(n);
    }

    public void visitFrame(int n, int n2, Object[] arrobject, int n3, Object[] arrobject2) {
        if (this.M == 0) {
            return;
        }
        if (n == -1) {
            int n4;
            if (this.x == null) {
                this.f();
            }
            this.T = n2;
            int n5 = this.a(this.r.b, n2, n3);
            for (n4 = 0; n4 < n2; ++n4) {
                this.z[n5++] = arrobject[n4] instanceof String ? 24117248 | this.b.c((String)arrobject[n4]) : (arrobject[n4] instanceof Integer ? (Integer)arrobject[n4] : 25165824 | this.b.a("", ((Label)arrobject[n4]).c));
            }
            for (n4 = 0; n4 < n3; ++n4) {
                this.z[n5++] = arrobject2[n4] instanceof String ? 24117248 | this.b.c((String)arrobject2[n4]) : (arrobject2[n4] instanceof Integer ? (Integer)arrobject2[n4] : 25165824 | this.b.a("", ((Label)arrobject2[n4]).c));
            }
            this.b();
        } else {
            int n6;
            if (this.v == null) {
                this.v = new ByteVector();
                n6 = this.r.b;
            } else {
                n6 = this.r.b - this.w - 1;
                if (n6 < 0) {
                    if (n == 3) {
                        return;
                    }
                    throw new IllegalStateException();
                }
            }
            switch (n) {
                case 0: {
                    int n7;
                    this.T = n2;
                    this.v.putByte(255).putShort(n6).putShort(n2);
                    for (n7 = 0; n7 < n2; ++n7) {
                        this.a(arrobject[n7]);
                    }
                    this.v.putShort(n3);
                    for (n7 = 0; n7 < n3; ++n7) {
                        this.a(arrobject2[n7]);
                    }
                    break;
                }
                case 1: {
                    this.T += n2;
                    this.v.putByte(251 + n2).putShort(n6);
                    for (int i = 0; i < n2; ++i) {
                        this.a(arrobject[i]);
                    }
                    break;
                }
                case 2: {
                    this.T -= n2;
                    this.v.putByte(251 - n2).putShort(n6);
                    break;
                }
                case 3: {
                    if (n6 < 64) {
                        this.v.putByte(n6);
                        break;
                    }
                    this.v.putByte(251).putShort(n6);
                    break;
                }
                case 4: {
                    if (n6 < 64) {
                        this.v.putByte(64 + n6);
                    } else {
                        this.v.putByte(247).putShort(n6);
                    }
                    this.a(arrobject2[0]);
                }
            }
            this.w = this.r.b;
            ++this.u;
        }
        this.s = Math.max(this.s, n3);
        this.t = Math.max(this.t, this.T);
    }

    public void visitInsn(int n) {
        this.Y = this.r.b;
        this.r.putByte(n);
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(n, 0, null, null);
            } else {
                int n2 = this.Q + Frame.a[n];
                if (n2 > this.R) {
                    this.R = n2;
                }
                this.Q = n2;
            }
            if (n >= 172 && n <= 177 || n == 191) {
                this.e();
            }
        }
    }

    public void visitVarInsn(int n, int n2) {
        int n3;
        this.Y = this.r.b;
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(n, n2, null, null);
            } else if (n == 169) {
                this.P.a |= 256;
                this.P.f = this.Q;
                this.e();
            } else {
                n3 = this.Q + Frame.a[n];
                if (n3 > this.R) {
                    this.R = n3;
                }
                this.Q = n3;
            }
        }
        if (this.M != 2) {
            n3 = n == 22 || n == 24 || n == 55 || n == 57 ? n2 + 2 : n2 + 1;
            if (n3 > this.t) {
                this.t = n3;
            }
        }
        if (n2 < 4 && n != 169) {
            n3 = n < 54 ? 26 + (n - 21 << 2) + n2 : 59 + (n - 54 << 2) + n2;
            this.r.putByte(n3);
        } else if (n2 >= 256) {
            this.r.putByte(196).b(n, n2);
        } else {
            this.r.a(n, n2);
        }
        if (n >= 54 && this.M == 0 && this.A > 0) {
            this.visitLabel(new Label());
        }
    }

    public void visitJumpInsn(int n, Label label) {
        this.Y = this.r.b;
        Label label2 = null;
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(n, 0, null, null);
                label.a().a |= 16;
                this.a(0, label);
                if (n != 167) {
                    label2 = new Label();
                }
            } else if (n == 168) {
                if ((label.a & 512) == 0) {
                    label.a |= 512;
                    ++this.L;
                }
                this.P.a |= 128;
                this.a(this.Q + 1, label);
                label2 = new Label();
            } else {
                this.Q += Frame.a[n];
                this.a(this.Q, label);
            }
        }
        if ((label.a & 2) != 0 && label.c - this.r.b < -32768) {
            if (n == 167) {
                this.r.putByte(200);
            } else if (n == 168) {
                this.r.putByte(201);
            } else {
                if (label2 != null) {
                    label2.a |= 16;
                }
                this.r.putByte(n <= 166 ? (n + 1 ^ 1) - 1 : n ^ 1);
                this.r.putShort(8);
                this.r.putByte(200);
            }
            label.a(this, this.r, this.r.b - 1, true);
        } else {
            this.r.putByte(n);
            label.a(this, this.r, this.r.b - 1, false);
        }
        if (this.P != null) {
            if (label2 != null) {
                this.visitLabel(label2);
            }
            if (n == 167) {
                this.e();
            }
        }
    }

    public void visitIincInsn(int n, int n2) {
        int n3;
        this.Y = this.r.b;
        if (this.P != null && this.M == 0) {
            this.P.h.a(132, n, null, null);
        }
        if (this.M != 2 && (n3 = n + 1) > this.t) {
            this.t = n3;
        }
        if (n > 255 || n2 > 127 || n2 < -128) {
            this.r.putByte(196).b(132, n).putShort(n2);
        } else {
            this.r.putByte(132).a(n, n2);
        }
    }

    public /* varargs */ void visitTableSwitchInsn(int n, int n2, Label label, Label ... arrlabel) {
        this.Y = this.r.b;
        int n3 = this.r.b;
        this.r.putByte(170);
        this.r.putByteArray(null, 0, (4 - this.r.b % 4) % 4);
        label.a(this, this.r, n3, true);
        this.r.putInt(n).putInt(n2);
        for (int i = 0; i < arrlabel.length; ++i) {
            arrlabel[i].a(this, this.r, n3, true);
        }
        this.a(label, arrlabel);
    }

    public void visitLookupSwitchInsn(Label label, int[] arrn, Label[] arrlabel) {
        this.Y = this.r.b;
        int n = this.r.b;
        this.r.putByte(171);
        this.r.putByteArray(null, 0, (4 - this.r.b % 4) % 4);
        label.a(this, this.r, n, true);
        this.r.putInt(arrlabel.length);
        for (int i = 0; i < arrlabel.length; ++i) {
            this.r.putInt(arrn[i]);
            arrlabel[i].a(this, this.r, n, true);
        }
        this.a(label, arrlabel);
    }

    public void visitIntInsn(int n, int n2) {
        this.Y = this.r.b;
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(n, n2, null, null);
            } else if (n != 188) {
                int n3 = this.Q + 1;
                if (n3 > this.R) {
                    this.R = n3;
                }
                this.Q = n3;
            }
        }
        if (n == 17) {
            this.r.b(n, n2);
        } else {
            this.r.a(n, n2);
        }
    }

    public /* varargs */ void visitInvokeDynamicInsn(String string, String string2, Handle handle, Object ... arrobject) {
        this.Y = this.r.b;
        Item item = this.b.a(string, string2, handle, arrobject);
        int n = item.c;
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(186, 0, this.b, item);
            } else {
                int n2;
                if (n == 0) {
                    item.c = n = Type.getArgumentsAndReturnSizes((String)string2);
                }
                if ((n2 = this.Q - (n >> 2) + (n & 3) + 1) > this.R) {
                    this.R = n2;
                }
                this.Q = n2;
            }
        }
        this.r.b(186, item.a);
        this.r.putShort(0);
    }

    public void visitTypeInsn(int n, String string) {
        this.Y = this.r.b;
        Item item = this.b.a(string);
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(n, this.r.b, this.b, item);
            } else if (n == 187) {
                int n2 = this.Q + 1;
                if (n2 > this.R) {
                    this.R = n2;
                }
                this.Q = n2;
            }
        }
        this.r.b(n, item.a);
    }

    public void visitMultiANewArrayInsn(String string, int n) {
        this.Y = this.r.b;
        Item item = this.b.a(string);
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(197, n, this.b, item);
            } else {
                this.Q += 1 - n;
            }
        }
        this.r.b(197, item.a).putByte(n);
    }

    public void visitLocalVariable(String string, String string2, String string3, Label label, Label label2, int n) {
        int n2;
        char c;
        if (string3 != null) {
            if (this.G == null) {
                this.G = new ByteVector();
            }
            ++this.F;
            this.G.putShort(label.c).putShort(label2.c - label.c).putShort(this.b.newUTF8(string)).putShort(this.b.newUTF8(string3)).putShort(n);
        }
        if (this.E == null) {
            this.E = new ByteVector();
        }
        ++this.D;
        this.E.putShort(label.c).putShort(label2.c - label.c).putShort(this.b.newUTF8(string)).putShort(this.b.newUTF8(string2)).putShort(n);
        if (this.M != 2 && (n2 = n + ((c = string2.charAt(0)) == 'J' || c == 'D' ? 2 : 1)) > this.t) {
            this.t = n2;
        }
    }

    public AnnotationVisitor visitLocalVariableAnnotation(int n, TypePath typePath, Label[] arrlabel, Label[] arrlabel2, int[] arrn, String string, boolean bl) {
        int n2;
        ByteVector byteVector = new ByteVector();
        byteVector.putByte(n >>> 24).putShort(arrlabel.length);
        for (n2 = 0; n2 < arrlabel.length; ++n2) {
            byteVector.putShort(arrlabel[n2].c).putShort(arrlabel2[n2].c - arrlabel[n2].c).putShort(arrn[n2]);
        }
        if (typePath == null) {
            byteVector.putByte(0);
        } else {
            n2 = typePath.a[typePath.b] * 2 + 1;
            byteVector.putByteArray(typePath.a, typePath.b, n2);
        }
        byteVector.putShort(this.b.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.b, true, byteVector, byteVector, byteVector.b - 2);
        if (bl) {
            annotationWriter.g = this.W;
            this.W = annotationWriter;
        } else {
            annotationWriter.g = this.X;
            this.X = annotationWriter;
        }
        return annotationWriter;
    }

    public void visitMaxs(int n, int n2) {
        if (this.K) {
            this.d();
        }
        if (this.M == 0) {
            Type[] arrtype;
            Object object;
            Object object2;
            int n3;
            Label label;
            Object object3;
            Handler handler = this.B;
            while (handler != null) {
                label = handler.a.a();
                arrtype = handler.c.a();
                Label label2 = handler.b.a();
                object = handler.d == null ? "java/lang/Throwable" : handler.d;
                int n4 = 24117248 | this.b.c((String)object);
                arrtype.a |= 16;
                while (label != label2) {
                    object3 = new Edge();
                    object3.a = n4;
                    object3.b = arrtype;
                    object3.c = label.j;
                    label.j = object3;
                    label = label.i;
                }
                handler = handler.f;
            }
            label = this.N.h;
            arrtype = Type.getArgumentTypes((String)this.f);
            label.a(this.b, this.c, arrtype, this.t);
            this.b((Frame)label);
            Object object4 = 0;
            object = this.N;
            while (object != null) {
                String string = object;
                object = object.k;
                string.k = null;
                label = string.h;
                if ((string.a & 16) != 0) {
                    string.a |= 32;
                }
                string.a |= 64;
                object3 = label.d.length + string.g;
                if (object3 > object4) {
                    object4 = object3;
                }
                Edge edge = string.j;
                while (edge != null) {
                    object2 = (Object)edge.b.a();
                    n3 = label.a(this.b, object2.h, edge.a) ? 1 : 0;
                    if (n3 != 0 && object2.k == null) {
                        object2.k = object;
                        object = object2;
                    }
                    edge = edge.c;
                }
            }
            Label label3 = this.N;
            while (label3 != null) {
                int n5;
                Label label4;
                label = label3.h;
                if ((label3.a & 32) != 0) {
                    this.b((Frame)label);
                }
                if ((label3.a & 64) == 0 && (object2 = ((label4 = label3.i) == null ? this.r.b : label4.c) - 1) >= (n5 = label3.c)) {
                    object4 = Math.max((int)object4, 1);
                    for (n3 = n5; n3 < object2; ++n3) {
                        this.r.a[n3] = 0;
                    }
                    this.r.a[object2] = -65;
                    n3 = this.a(n5, 0, 1);
                    this.z[n3] = 24117248 | this.b.c("java/lang/Throwable");
                    this.b();
                    this.B = Handler.a((Handler)this.B, (Label)label3, (Label)label4);
                }
                label3 = label3.i;
            }
            handler = this.B;
            this.A = 0;
            while (handler != null) {
                ++this.A;
                handler = handler.f;
            }
            this.s = object4;
        } else if (this.M == 1) {
            Edge edge;
            Label label;
            Label label5;
            Handler handler = this.B;
            while (handler != null) {
                Label label6 = handler.a;
                label5 = handler.c;
                label = handler.b;
                while (label6 != label) {
                    edge = new Edge();
                    edge.a = Integer.MAX_VALUE;
                    edge.b = label5;
                    if ((label6.a & 128) == 0) {
                        edge.c = label6.j;
                        label6.j = edge;
                    } else {
                        edge.c = label6.j.c.c;
                        label6.j.c.c = edge;
                    }
                    label6 = label6.i;
                }
                handler = handler.f;
            }
            if (this.L > 0) {
                int n6 = 0;
                this.N.b(null, 1L, this.L);
                label5 = this.N;
                while (label5 != null) {
                    if ((label5.a & 128) != 0) {
                        label = label5.j.c.b;
                        if ((label.a & 1024) == 0) {
                            label.b(null, (long)n6 / 32L << 32 | 1L << ++n6 % 32, this.L);
                        }
                    }
                    label5 = label5.i;
                }
                label5 = this.N;
                while (label5 != null) {
                    if ((label5.a & 128) != 0) {
                        label = this.N;
                        while (label != null) {
                            label.a &= -2049;
                            label = label.i;
                        }
                        edge = label5.j.c.b;
                        edge.b(label5, 0L, this.L);
                    }
                    label5 = label5.i;
                }
            }
            int n7 = 0;
            label5 = this.N;
            while (label5 != null) {
                label = label5;
                label5 = label5.k;
                int n8 = label.f;
                int n9 = n8 + label.g;
                if (n9 > n7) {
                    n7 = n9;
                }
                Edge edge2 = label.j;
                if ((label.a & 128) != 0) {
                    edge2 = edge2.c;
                }
                while (edge2 != null) {
                    label = edge2.b;
                    if ((label.a & 8) == 0) {
                        label.f = edge2.a == Integer.MAX_VALUE ? 1 : n8 + edge2.a;
                        label.a |= 8;
                        label.k = label5;
                        label5 = label;
                    }
                    edge2 = edge2.c;
                }
            }
            this.s = Math.max(n, n7);
        } else {
            this.s = n;
            this.t = n2;
        }
    }

    public AnnotationVisitor visitTryCatchAnnotation(int n, TypePath typePath, String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.a((int)n, (TypePath)typePath, (ByteVector)byteVector);
        byteVector.putShort(this.b.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.b, true, byteVector, byteVector, byteVector.b - 2);
        if (bl) {
            annotationWriter.g = this.W;
            this.W = annotationWriter;
        } else {
            annotationWriter.g = this.X;
            this.X = annotationWriter;
        }
        return annotationWriter;
    }

    public AnnotationVisitor visitParameterAnnotation(int n, String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        if ("Ljava/lang/Synthetic;".equals(string)) {
            this.S = Math.max(this.S, n + 1);
            return new AnnotationWriter(this.b, false, byteVector, null, 0);
        }
        byteVector.putShort(this.b.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.b, true, byteVector, byteVector, 2);
        if (bl) {
            if (this.o == null) {
                this.o = new AnnotationWriter[Type.getArgumentTypes((String)this.f).length];
            }
            annotationWriter.g = this.o[n];
            this.o[n] = annotationWriter;
        } else {
            if (this.p == null) {
                this.p = new AnnotationWriter[Type.getArgumentTypes((String)this.f).length];
            }
            annotationWriter.g = this.p[n];
            this.p[n] = annotationWriter;
        }
        return annotationWriter;
    }

    private void e() {
        if (this.M == 0) {
            Label label = new Label();
            label.h = new Frame();
            label.h.b = label;
            label.a(this, this.r.b, this.r.a);
            this.O.i = label;
            this.O = label;
        } else {
            this.P.g = this.R;
        }
        this.P = null;
    }

    private void d() {
        int n;
        int[] arrn;
        int n2;
        ByteVector byteVector;
        int n3;
        int n4;
        int n5;
        byte[] arrby = this.r.a;
        ByteVector byteVector2 = new ByteVector[]{};
        int[] arrn2 = new int[]{};
        boolean[] arrbl = new boolean[this.r.b];
        int n6 = 3;
        do {
            if (n6 == 3) {
                n6 = 2;
            }
            n3 = 0;
            while (n3 < arrby.length) {
                int n7 = arrby[n3] & 255;
                n5 = 0;
                switch (ClassWriter.a[n7]) {
                    case 0: 
                    case 4: {
                        ++n3;
                        break;
                    }
                    case 9: {
                        if (n7 > 201) {
                            n7 = n7 < 218 ? n7 - 49 : n7 - 20;
                            n4 = n3 + MethodWriter.c((byte[])arrby, (int)(n3 + 1));
                        } else {
                            n4 = n3 + MethodWriter.b((byte[])arrby, (int)(n3 + 1));
                        }
                        n2 = MethodWriter.a((int[])byteVector2, (int[])arrn2, (int)n3, (int)n4);
                        if (!(n2 >= -32768 && n2 <= 32767 || arrbl[n3])) {
                            n5 = n7 == 167 || n7 == 168 ? 2 : 5;
                            arrbl[n3] = true;
                        }
                        n3 += 3;
                        break;
                    }
                    case 10: {
                        n3 += 5;
                        break;
                    }
                    case 14: {
                        if (n6 == 1) {
                            n2 = MethodWriter.a((int[])byteVector2, (int[])arrn2, (int)0, (int)n3);
                            n5 = - (n2 & 3);
                        } else if (!arrbl[n3]) {
                            n5 = n3 & 3;
                            arrbl[n3] = true;
                        }
                        n3 = n3 + 4 - (n3 & 3);
                        n3 += 4 * (MethodWriter.a((byte[])arrby, (int)(n3 + 8)) - MethodWriter.a((byte[])arrby, (int)(n3 + 4)) + 1) + 12;
                        break;
                    }
                    case 15: {
                        if (n6 == 1) {
                            n2 = MethodWriter.a((int[])byteVector2, (int[])arrn2, (int)0, (int)n3);
                            n5 = - (n2 & 3);
                        } else if (!arrbl[n3]) {
                            n5 = n3 & 3;
                            arrbl[n3] = true;
                        }
                        n3 = n3 + 4 - (n3 & 3);
                        n3 += 8 * MethodWriter.a((byte[])arrby, (int)(n3 + 4)) + 8;
                        break;
                    }
                    case 17: {
                        n7 = arrby[n3 + 1] & 255;
                        if (n7 == 132) {
                            n3 += 6;
                            break;
                        }
                        n3 += 4;
                        break;
                    }
                    case 1: 
                    case 3: 
                    case 11: {
                        n3 += 2;
                        break;
                    }
                    case 2: 
                    case 5: 
                    case 6: 
                    case 12: 
                    case 13: {
                        n3 += 3;
                        break;
                    }
                    case 7: 
                    case 8: {
                        n3 += 5;
                        break;
                    }
                    default: {
                        n3 += 4;
                    }
                }
                if (n5 == 0) continue;
                byteVector = new int[byteVector2.length + 1];
                arrn = new int[arrn2.length + 1];
                System.arraycopy((Object)byteVector2, 0, (Object)byteVector, 0, byteVector2.length);
                System.arraycopy(arrn2, 0, arrn, 0, arrn2.length);
                byteVector[byteVector2.length] = n3;
                arrn[arrn2.length] = n5;
                byteVector2 = byteVector;
                arrn2 = arrn;
                if (n5 <= 0) continue;
                n6 = 3;
            }
            if (n6 >= 3) continue;
            --n6;
        } while (n6 != 0);
        ByteVector byteVector3 = new ByteVector(this.r.b);
        n3 = 0;
        block24 : while (n3 < this.r.b) {
            n5 = arrby[n3] & 255;
            switch (ClassWriter.a[n5]) {
                int n8;
                int n9;
                case 0: 
                case 4: {
                    byteVector3.putByte(n5);
                    ++n3;
                    continue block24;
                }
                case 9: {
                    if (n5 > 201) {
                        n5 = n5 < 218 ? n5 - 49 : n5 - 20;
                        n4 = n3 + MethodWriter.c((byte[])arrby, (int)(n3 + 1));
                    } else {
                        n4 = n3 + MethodWriter.b((byte[])arrby, (int)(n3 + 1));
                    }
                    n2 = MethodWriter.a((int[])byteVector2, (int[])arrn2, (int)n3, (int)n4);
                    if (arrbl[n3]) {
                        if (n5 == 167) {
                            byteVector3.putByte(200);
                        } else if (n5 == 168) {
                            byteVector3.putByte(201);
                        } else {
                            byteVector3.putByte(n5 <= 166 ? (n5 + 1 ^ 1) - 1 : n5 ^ 1);
                            byteVector3.putShort(8);
                            byteVector3.putByte(200);
                            n2 -= 3;
                        }
                        byteVector3.putInt(n2);
                    } else {
                        byteVector3.putByte(n5);
                        byteVector3.putShort(n2);
                    }
                    n3 += 3;
                    continue block24;
                }
                case 10: {
                    n4 = n3 + MethodWriter.a((byte[])arrby, (int)(n3 + 1));
                    n2 = MethodWriter.a((int[])byteVector2, (int[])arrn2, (int)n3, (int)n4);
                    byteVector3.putByte(n5);
                    byteVector3.putInt(n2);
                    n3 += 5;
                    continue block24;
                }
                case 14: {
                    n8 = n3;
                    n3 = n3 + 4 - (n8 & 3);
                    byteVector3.putByte(170);
                    byteVector3.putByteArray(null, 0, (4 - byteVector3.b % 4) % 4);
                    n4 = n8 + MethodWriter.a((byte[])arrby, (int)n3);
                    n2 = MethodWriter.a((int[])byteVector2, (int[])arrn2, (int)n8, (int)n4);
                    byteVector3.putInt(n2);
                    n9 = MethodWriter.a((byte[])arrby, (int)(n3 += 4));
                    byteVector3.putInt(n9);
                    byteVector3.putInt(MethodWriter.a((byte[])arrby, (int)((n3 += 4) - 4)));
                    for (n9 = MethodWriter.a((byte[])arrby, (int)(n3 += 4)) - n9 + 1; n9 > 0; --n9) {
                        n4 = n8 + MethodWriter.a((byte[])arrby, (int)n3);
                        n3 += 4;
                        n2 = MethodWriter.a((int[])byteVector2, (int[])arrn2, (int)n8, (int)n4);
                        byteVector3.putInt(n2);
                    }
                    continue block24;
                }
                case 15: {
                    n8 = n3;
                    n3 = n3 + 4 - (n8 & 3);
                    byteVector3.putByte(171);
                    byteVector3.putByteArray(null, 0, (4 - byteVector3.b % 4) % 4);
                    n4 = n8 + MethodWriter.a((byte[])arrby, (int)n3);
                    n2 = MethodWriter.a((int[])byteVector2, (int[])arrn2, (int)n8, (int)n4);
                    byteVector3.putInt(n2);
                    n3 += 4;
                    byteVector3.putInt(n9);
                    for (n9 = MethodWriter.a((byte[])arrby, (int)(n3 += 4)); n9 > 0; --n9) {
                        byteVector3.putInt(MethodWriter.a((byte[])arrby, (int)n3));
                        n4 = n8 + MethodWriter.a((byte[])arrby, (int)(n3 += 4));
                        n3 += 4;
                        n2 = MethodWriter.a((int[])byteVector2, (int[])arrn2, (int)n8, (int)n4);
                        byteVector3.putInt(n2);
                    }
                    continue block24;
                }
                case 17: {
                    n5 = arrby[n3 + 1] & 255;
                    if (n5 == 132) {
                        byteVector3.putByteArray(arrby, n3, 6);
                        n3 += 6;
                        continue block24;
                    }
                    byteVector3.putByteArray(arrby, n3, 4);
                    n3 += 4;
                    continue block24;
                }
                case 1: 
                case 3: 
                case 11: {
                    byteVector3.putByteArray(arrby, n3, 2);
                    n3 += 2;
                    continue block24;
                }
                case 2: 
                case 5: 
                case 6: 
                case 12: 
                case 13: {
                    byteVector3.putByteArray(arrby, n3, 3);
                    n3 += 3;
                    continue block24;
                }
                case 7: 
                case 8: {
                    byteVector3.putByteArray(arrby, n3, 5);
                    n3 += 5;
                    continue block24;
                }
            }
            byteVector3.putByteArray(arrby, n3, 4);
            n3 += 4;
        }
        if (this.M == 0) {
            Label label = this.N;
            while (label != null) {
                n3 = label.c - 3;
                if (n3 >= 0 && arrbl[n3]) {
                    label.a |= 16;
                }
                MethodWriter.a((int[])byteVector2, (int[])arrn2, (Label)label);
                label = label.i;
            }
            if (this.b.H != null) {
                for (n = 0; n < this.b.H.length; ++n) {
                    byteVector = this.b.H[n];
                    if (byteVector == null || byteVector.b != 31) continue;
                    byteVector.c = MethodWriter.a((int[])byteVector2, (int[])arrn2, (int)0, (int)byteVector.c);
                }
            }
        } else if (this.u > 0) {
            this.b.L = true;
        }
        Handler handler = this.B;
        while (handler != null) {
            MethodWriter.a((int[])byteVector2, (int[])arrn2, (Label)handler.a);
            MethodWriter.a((int[])byteVector2, (int[])arrn2, (Label)handler.b);
            MethodWriter.a((int[])byteVector2, (int[])arrn2, (Label)handler.c);
            handler = handler.f;
        }
        for (n = 0; n < 2; ++n) {
            ByteVector byteVector4 = byteVector = n == 0 ? this.E : this.G;
            if (byteVector == null) continue;
            arrby = byteVector.a;
            for (n3 = 0; n3 < byteVector.b; n3 += 10) {
                n4 = MethodWriter.c((byte[])arrby, (int)n3);
                n2 = MethodWriter.a((int[])byteVector2, (int[])arrn2, (int)0, (int)n4);
                MethodWriter.a((byte[])arrby, (int)n3, (int)n2);
                n2 = MethodWriter.a((int[])byteVector2, (int[])arrn2, (int)0, (int)(n4 += MethodWriter.c((byte[])arrby, (int)(n3 + 2)))) - n2;
                MethodWriter.a((byte[])arrby, (int)(n3 + 2), (int)n2);
            }
        }
        if (this.I != null) {
            arrby = this.I.a;
            for (n3 = 0; n3 < this.I.b; n3 += 4) {
                MethodWriter.a((byte[])arrby, (int)n3, (int)MethodWriter.a((int[])byteVector2, (int[])arrn2, (int)0, (int)MethodWriter.c((byte[])arrby, (int)n3)));
            }
        }
        byteVector = this.J;
        while (byteVector != null) {
            arrn = byteVector.getLabels();
            if (arrn != null) {
                for (n = arrn.length - 1; n >= 0; --n) {
                    MethodWriter.a((int[])byteVector2, (int[])arrn2, (Label)arrn[n]);
                }
            }
            byteVector = byteVector.a;
        }
        this.r = byteVector3;
    }

    private void f() {
        int n = this.a(0, this.f.length() + 1, 0);
        if ((this.c & 8) == 0) {
            this.z[n++] = (this.c & 524288) == 0 ? 24117248 | this.b.c(this.b.I) : 6;
        }
        int n2 = 1;
        block8 : do {
            int n3 = n2;
            switch (this.f.charAt(n2++)) {
                case 'B': 
                case 'C': 
                case 'I': 
                case 'S': 
                case 'Z': {
                    this.z[n++] = 1;
                    continue block8;
                }
                case 'F': {
                    this.z[n++] = 2;
                    continue block8;
                }
                case 'J': {
                    this.z[n++] = 4;
                    continue block8;
                }
                case 'D': {
                    this.z[n++] = 3;
                    continue block8;
                }
                case '[': {
                    while (this.f.charAt(n2) == '[') {
                        ++n2;
                    }
                    if (this.f.charAt(n2) == 'L') {
                        ++n2;
                        while (this.f.charAt(n2) != ';') {
                            ++n2;
                        }
                    }
                    this.z[n++] = 24117248 | this.b.c(this.f.substring(n3, ++n2));
                    continue block8;
                }
                case 'L': {
                    while (this.f.charAt(n2) != ';') {
                        ++n2;
                    }
                    this.z[n++] = 24117248 | this.b.c(this.f.substring(n3 + 1, n2++));
                    continue block8;
                }
            }
            break;
        } while (true);
        this.z[1] = n - 3;
        this.b();
    }

    static short b(byte[] arrby, int n) {
        return (short)((arrby[n] & 255) << 8 | arrby[n + 1] & 255);
    }

    private void b(Frame frame) {
        int n;
        int n2;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int[] arrn = frame.c;
        int[] arrn2 = frame.d;
        for (n = 0; n < arrn.length; ++n) {
            n2 = arrn[n];
            if (n2 == 16777216) {
                ++n3;
            } else {
                n4 += n3 + 1;
                n3 = 0;
            }
            if (n2 != 16777220 && n2 != 16777219) continue;
            ++n;
        }
        for (n = 0; n < arrn2.length; ++n) {
            n2 = arrn2[n];
            ++n5;
            if (n2 != 16777220 && n2 != 16777219) continue;
            ++n;
        }
        int n6 = this.a(frame.b.c, n4, n5);
        n = 0;
        while (n4 > 0) {
            n2 = arrn[n];
            this.z[n6++] = n2;
            if (n2 == 16777220 || n2 == 16777219) {
                ++n;
            }
            ++n;
            --n4;
        }
        for (n = 0; n < arrn2.length; ++n) {
            n2 = arrn2[n];
            this.z[n6++] = n2;
            if (n2 != 16777220 && n2 != 16777219) continue;
            ++n;
        }
        this.b();
    }

    private void b() {
        if (this.x != null) {
            if (this.v == null) {
                this.v = new ByteVector();
            }
            this.c();
            ++this.u;
        }
        this.x = this.z;
        this.z = null;
    }

    static int a(byte[] arrby, int n) {
        return (arrby[n] & 255) << 24 | (arrby[n + 1] & 255) << 16 | (arrby[n + 2] & 255) << 8 | arrby[n + 3] & 255;
    }

    static void a(int[] arrn, int[] arrn2, Label label) {
        if ((label.a & 4) == 0) {
            label.c = MethodWriter.a((int[])arrn, (int[])arrn2, (int)0, (int)label.c);
            label.a |= 4;
        }
    }

    static void a(byte[] arrby, int n, int n2) {
        arrby[n] = (byte)(n2 >>> 8);
        arrby[n + 1] = (byte)n2;
    }

    static int a(int[] arrn, int[] arrn2, int n, int n2) {
        int n3 = n2 - n;
        for (int i = 0; i < arrn.length; ++i) {
            if (n < arrn[i] && arrn[i] <= n2) {
                n3 += arrn2[i];
                continue;
            }
            if (n2 >= arrn[i] || arrn[i] > n) continue;
            n3 -= arrn2[i];
        }
        return n3;
    }

    private void a(int n, int n2) {
        block13 : for (int i = n; i < n2; ++i) {
            int n3 = this.z[i];
            int n4 = n3 & -268435456;
            if (n4 == 0) {
                int n5 = n3 & 1048575;
                switch (n3 & 267386880) {
                    case 24117248: {
                        this.v.putByte(7).putShort(this.b.newClass(this.b.H[n5].g));
                        continue block13;
                    }
                    case 25165824: {
                        this.v.putByte(8).putShort(this.b.H[n5].c);
                        continue block13;
                    }
                }
                this.v.putByte(n5);
                continue;
            }
            StringBuffer stringBuffer = new StringBuffer();
            n4 >>= 28;
            while (n4-- > 0) {
                stringBuffer.append('[');
            }
            if ((n3 & 267386880) == 24117248) {
                stringBuffer.append('L');
                stringBuffer.append(this.b.H[n3 & 1048575].g);
                stringBuffer.append(';');
            } else {
                switch (n3 & 15) {
                    case 1: {
                        stringBuffer.append('I');
                        break;
                    }
                    case 2: {
                        stringBuffer.append('F');
                        break;
                    }
                    case 3: {
                        stringBuffer.append('D');
                        break;
                    }
                    case 9: {
                        stringBuffer.append('Z');
                        break;
                    }
                    case 10: {
                        stringBuffer.append('B');
                        break;
                    }
                    case 11: {
                        stringBuffer.append('C');
                        break;
                    }
                    case 12: {
                        stringBuffer.append('S');
                        break;
                    }
                    default: {
                        stringBuffer.append('J');
                    }
                }
            }
            this.v.putByte(7).putShort(this.b.newClass(stringBuffer.toString()));
        }
    }

    private int a(int n, int n2, int n3) {
        int n4 = 3 + n2 + n3;
        if (this.z == null || this.z.length < n4) {
            this.z = new int[n4];
        }
        this.z[0] = n;
        this.z[1] = n2;
        this.z[2] = n3;
        return 3;
    }

    private void a(int n, Label label) {
        Edge edge = new Edge();
        edge.a = n;
        edge.b = label;
        edge.c = this.P.j;
        this.P.j = edge;
    }

    private void a(Label label, Label[] arrlabel) {
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(171, 0, null, null);
                this.a(0, label);
                label.a().a |= 16;
                for (int i = 0; i < arrlabel.length; ++i) {
                    this.a(0, arrlabel[i]);
                    arrlabel[i].a().a |= 16;
                }
            } else {
                --this.Q;
                this.a(this.Q, label);
                for (int i = 0; i < arrlabel.length; ++i) {
                    this.a(this.Q, arrlabel[i]);
                }
            }
            this.e();
        }
    }

    private void a(Object object) {
        if (object instanceof String) {
            this.v.putByte(7).putShort(this.b.newClass((String)object));
        } else if (object instanceof Integer) {
            this.v.putByte(((Integer)object).intValue());
        } else {
            this.v.putByte(8).putShort(((Label)object).c);
        }
    }

    final int a() {
        int n;
        if (this.h != 0) {
            return 6 + this.i;
        }
        int n2 = 8;
        if (this.r.b > 0) {
            if (this.r.b > 65535) {
                throw new RuntimeException("Method code too large!");
            }
            this.b.newUTF8("Code");
            n2 += 18 + this.r.b + 8 * this.A;
            if (this.E != null) {
                this.b.newUTF8("LocalVariableTable");
                n2 += 8 + this.E.b;
            }
            if (this.G != null) {
                this.b.newUTF8("LocalVariableTypeTable");
                n2 += 8 + this.G.b;
            }
            if (this.I != null) {
                this.b.newUTF8("LineNumberTable");
                n2 += 8 + this.I.b;
            }
            if (this.v != null) {
                n = (this.b.b & 65535) >= 50 ? 1 : 0;
                this.b.newUTF8(n != 0 ? "StackMapTable" : "StackMap");
                n2 += 8 + this.v.b;
            }
            if (this.W != null) {
                this.b.newUTF8("RuntimeVisibleTypeAnnotations");
                n2 += 8 + this.W.a();
            }
            if (this.X != null) {
                this.b.newUTF8("RuntimeInvisibleTypeAnnotations");
                n2 += 8 + this.X.a();
            }
            if (this.J != null) {
                n2 += this.J.a(this.b, this.r.a, this.r.b, this.s, this.t);
            }
        }
        if (this.j > 0) {
            this.b.newUTF8("Exceptions");
            n2 += 8 + 2 * this.j;
        }
        if ((this.c & 4096) != 0 && ((this.b.b & 65535) < 49 || (this.c & 262144) != 0)) {
            this.b.newUTF8("Synthetic");
            n2 += 6;
        }
        if ((this.c & 131072) != 0) {
            this.b.newUTF8("Deprecated");
            n2 += 6;
        }
        if (this.g != null) {
            this.b.newUTF8("Signature");
            this.b.newUTF8(this.g);
            n2 += 8;
        }
        if (this.$ != null) {
            this.b.newUTF8("MethodParameters");
            n2 += 7 + this.$.b;
        }
        if (this.l != null) {
            this.b.newUTF8("AnnotationDefault");
            n2 += 6 + this.l.b;
        }
        if (this.m != null) {
            this.b.newUTF8("RuntimeVisibleAnnotations");
            n2 += 8 + this.m.a();
        }
        if (this.n != null) {
            this.b.newUTF8("RuntimeInvisibleAnnotations");
            n2 += 8 + this.n.a();
        }
        if (this.U != null) {
            this.b.newUTF8("RuntimeVisibleTypeAnnotations");
            n2 += 8 + this.U.a();
        }
        if (this.V != null) {
            this.b.newUTF8("RuntimeInvisibleTypeAnnotations");
            n2 += 8 + this.V.a();
        }
        if (this.o != null) {
            this.b.newUTF8("RuntimeVisibleParameterAnnotations");
            n2 += 7 + 2 * (this.o.length - this.S);
            for (n = this.o.length - 1; n >= this.S; --n) {
                n2 += this.o[n] == null ? 0 : this.o[n].a();
            }
        }
        if (this.p != null) {
            this.b.newUTF8("RuntimeInvisibleParameterAnnotations");
            n2 += 7 + 2 * (this.p.length - this.S);
            for (n = this.p.length - 1; n >= this.S; --n) {
                n2 += this.p[n] == null ? 0 : this.p[n].a();
            }
        }
        if (this.q != null) {
            n2 += this.q.a(this.b, null, 0, -1, -1);
        }
        return n2;
    }

    final void a(ByteVector byteVector) {
        int n;
        int n2 = 64;
        int n3 = 917504 | (this.c & 262144) / 64;
        byteVector.putShort(this.c & ~ n3).putShort(this.d).putShort(this.e);
        if (this.h != 0) {
            byteVector.putByteArray(this.b.M.b, this.h, this.i);
            return;
        }
        int n4 = 0;
        if (this.r.b > 0) {
            ++n4;
        }
        if (this.j > 0) {
            ++n4;
        }
        if ((this.c & 4096) != 0 && ((this.b.b & 65535) < 49 || (this.c & 262144) != 0)) {
            ++n4;
        }
        if ((this.c & 131072) != 0) {
            ++n4;
        }
        if (this.g != null) {
            ++n4;
        }
        if (this.$ != null) {
            ++n4;
        }
        if (this.l != null) {
            ++n4;
        }
        if (this.m != null) {
            ++n4;
        }
        if (this.n != null) {
            ++n4;
        }
        if (this.U != null) {
            ++n4;
        }
        if (this.V != null) {
            ++n4;
        }
        if (this.o != null) {
            ++n4;
        }
        if (this.p != null) {
            ++n4;
        }
        if (this.q != null) {
            n4 += this.q.a();
        }
        byteVector.putShort(n4);
        if (this.r.b > 0) {
            n = 12 + this.r.b + 8 * this.A;
            if (this.E != null) {
                n += 8 + this.E.b;
            }
            if (this.G != null) {
                n += 8 + this.G.b;
            }
            if (this.I != null) {
                n += 8 + this.I.b;
            }
            if (this.v != null) {
                n += 8 + this.v.b;
            }
            if (this.W != null) {
                n += 8 + this.W.a();
            }
            if (this.X != null) {
                n += 8 + this.X.a();
            }
            if (this.J != null) {
                n += this.J.a(this.b, this.r.a, this.r.b, this.s, this.t);
            }
            byteVector.putShort(this.b.newUTF8("Code")).putInt(n);
            byteVector.putShort(this.s).putShort(this.t);
            byteVector.putInt(this.r.b).putByteArray(this.r.a, 0, this.r.b);
            byteVector.putShort(this.A);
            if (this.A > 0) {
                Handler handler = this.B;
                while (handler != null) {
                    byteVector.putShort(handler.a.c).putShort(handler.b.c).putShort(handler.c.c).putShort(handler.e);
                    handler = handler.f;
                }
            }
            n4 = 0;
            if (this.E != null) {
                ++n4;
            }
            if (this.G != null) {
                ++n4;
            }
            if (this.I != null) {
                ++n4;
            }
            if (this.v != null) {
                ++n4;
            }
            if (this.W != null) {
                ++n4;
            }
            if (this.X != null) {
                ++n4;
            }
            if (this.J != null) {
                n4 += this.J.a();
            }
            byteVector.putShort(n4);
            if (this.E != null) {
                byteVector.putShort(this.b.newUTF8("LocalVariableTable"));
                byteVector.putInt(this.E.b + 2).putShort(this.D);
                byteVector.putByteArray(this.E.a, 0, this.E.b);
            }
            if (this.G != null) {
                byteVector.putShort(this.b.newUTF8("LocalVariableTypeTable"));
                byteVector.putInt(this.G.b + 2).putShort(this.F);
                byteVector.putByteArray(this.G.a, 0, this.G.b);
            }
            if (this.I != null) {
                byteVector.putShort(this.b.newUTF8("LineNumberTable"));
                byteVector.putInt(this.I.b + 2).putShort(this.H);
                byteVector.putByteArray(this.I.a, 0, this.I.b);
            }
            if (this.v != null) {
                boolean bl = (this.b.b & 65535) >= 50;
                byteVector.putShort(this.b.newUTF8(bl ? "StackMapTable" : "StackMap"));
                byteVector.putInt(this.v.b + 2).putShort(this.u);
                byteVector.putByteArray(this.v.a, 0, this.v.b);
            }
            if (this.W != null) {
                byteVector.putShort(this.b.newUTF8("RuntimeVisibleTypeAnnotations"));
                this.W.a(byteVector);
            }
            if (this.X != null) {
                byteVector.putShort(this.b.newUTF8("RuntimeInvisibleTypeAnnotations"));
                this.X.a(byteVector);
            }
            if (this.J != null) {
                this.J.a(this.b, this.r.a, this.r.b, this.t, this.s, byteVector);
            }
        }
        if (this.j > 0) {
            byteVector.putShort(this.b.newUTF8("Exceptions")).putInt(2 * this.j + 2);
            byteVector.putShort(this.j);
            for (n = 0; n < this.j; ++n) {
                byteVector.putShort(this.k[n]);
            }
        }
        if ((this.c & 4096) != 0 && ((this.b.b & 65535) < 49 || (this.c & 262144) != 0)) {
            byteVector.putShort(this.b.newUTF8("Synthetic")).putInt(0);
        }
        if ((this.c & 131072) != 0) {
            byteVector.putShort(this.b.newUTF8("Deprecated")).putInt(0);
        }
        if (this.g != null) {
            byteVector.putShort(this.b.newUTF8("Signature")).putInt(2).putShort(this.b.newUTF8(this.g));
        }
        if (this.$ != null) {
            byteVector.putShort(this.b.newUTF8("MethodParameters"));
            byteVector.putInt(this.$.b + 1).putByte(this.Z);
            byteVector.putByteArray(this.$.a, 0, this.$.b);
        }
        if (this.l != null) {
            byteVector.putShort(this.b.newUTF8("AnnotationDefault"));
            byteVector.putInt(this.l.b);
            byteVector.putByteArray(this.l.a, 0, this.l.b);
        }
        if (this.m != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeVisibleAnnotations"));
            this.m.a(byteVector);
        }
        if (this.n != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeInvisibleAnnotations"));
            this.n.a(byteVector);
        }
        if (this.U != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeVisibleTypeAnnotations"));
            this.U.a(byteVector);
        }
        if (this.V != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeInvisibleTypeAnnotations"));
            this.V.a(byteVector);
        }
        if (this.o != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeVisibleParameterAnnotations"));
            AnnotationWriter.a((AnnotationWriter[])this.o, (int)this.S, (ByteVector)byteVector);
        }
        if (this.p != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeInvisibleParameterAnnotations"));
            AnnotationWriter.a((AnnotationWriter[])this.p, (int)this.S, (ByteVector)byteVector);
        }
        if (this.q != null) {
            this.q.a(this.b, null, 0, -1, -1, byteVector);
        }
    }
}

