/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.AnnotationVisitor
 *  com.zhiyin.jagent.asm.Attribute
 *  com.zhiyin.jagent.asm.ByteVector
 *  com.zhiyin.jagent.asm.ClassReader
 *  com.zhiyin.jagent.asm.ClassVisitor
 *  com.zhiyin.jagent.asm.ClassWriter
 *  com.zhiyin.jagent.asm.Context
 *  com.zhiyin.jagent.asm.FieldVisitor
 *  com.zhiyin.jagent.asm.Handle
 *  com.zhiyin.jagent.asm.Item
 *  com.zhiyin.jagent.asm.Label
 *  com.zhiyin.jagent.asm.MethodVisitor
 *  com.zhiyin.jagent.asm.MethodWriter
 *  com.zhiyin.jagent.asm.Opcodes
 *  com.zhiyin.jagent.asm.Type
 *  com.zhiyin.jagent.asm.TypePath
 */
package com.zhiyin.jagent.asm;

import com.zhiyin.jagent.asm.AnnotationVisitor;
import com.zhiyin.jagent.asm.Attribute;
import com.zhiyin.jagent.asm.ByteVector;
import com.zhiyin.jagent.asm.ClassVisitor;
import com.zhiyin.jagent.asm.ClassWriter;
import com.zhiyin.jagent.asm.Context;
import com.zhiyin.jagent.asm.FieldVisitor;
import com.zhiyin.jagent.asm.Handle;
import com.zhiyin.jagent.asm.Item;
import com.zhiyin.jagent.asm.Label;
import com.zhiyin.jagent.asm.MethodVisitor;
import com.zhiyin.jagent.asm.MethodWriter;
import com.zhiyin.jagent.asm.Opcodes;
import com.zhiyin.jagent.asm.Type;
import com.zhiyin.jagent.asm.TypePath;
import java.io.IOException;
import java.io.InputStream;

/*
 * Exception performing whole class analysis ignored.
 */
public class ClassReader {
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    public static final int EXPAND_FRAMES = 8;
    public final byte[] b;
    private final int[] a;
    private final String[] c;
    private final int d;
    public final int header;

    public ClassReader(String string) throws IOException {
        this(ClassReader.a((InputStream)ClassLoader.getSystemResourceAsStream(string.replace('.', '/') + ".class"), (boolean)true));
    }

    public ClassReader(InputStream inputStream) throws IOException {
        this(ClassReader.a((InputStream)inputStream, (boolean)false));
    }

    public ClassReader(byte[] arrby) {
        this(arrby, 0, arrby.length);
    }

    public ClassReader(byte[] arrby, int n, int n2) {
        this.b = arrby;
        if (this.readShort(n + 6) > 52) {
            throw new IllegalArgumentException();
        }
        this.a = new int[this.readUnsignedShort(n + 8)];
        int n3 = this.a.length;
        this.c = new String[n3];
        int n4 = 0;
        int n5 = n + 10;
        for (int i = 1; i < n3; ++i) {
            int n6;
            this.a[i] = n5 + 1;
            switch (arrby[n5]) {
                case 3: 
                case 4: 
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 18: {
                    n6 = 5;
                    break;
                }
                case 5: 
                case 6: {
                    n6 = 9;
                    ++i;
                    break;
                }
                case 1: {
                    n6 = 3 + this.readUnsignedShort(n5 + 1);
                    if (n6 <= n4) break;
                    n4 = n6;
                    break;
                }
                case 15: {
                    n6 = 4;
                    break;
                }
                default: {
                    n6 = 3;
                }
            }
            n5 += n6;
        }
        this.d = n4;
        this.header = n5;
    }

    public String[] getInterfaces() {
        int n = this.header + 6;
        int n2 = this.readUnsignedShort(n);
        String[] arrstring = new String[n2];
        if (n2 > 0) {
            char[] arrc = new char[this.d];
            for (int i = 0; i < n2; ++i) {
                arrstring[i] = this.readClass(n += 2, arrc);
            }
        }
        return arrstring;
    }

    public void accept(ClassVisitor classVisitor, int n) {
        this.accept(classVisitor, new Attribute[0], n);
    }

    public void accept(ClassVisitor classVisitor, Attribute[] arrattribute, int n) {
        String string;
        int n2;
        int n3 = this.header;
        char[] arrc = new char[this.d];
        Context context = new Context();
        context.a = arrattribute;
        context.b = n;
        context.c = arrc;
        int n4 = this.readUnsignedShort(n3);
        String string2 = this.readClass(n3 + 2, arrc);
        String string3 = this.readClass(n3 + 4, arrc);
        String[] arrstring = new String[this.readUnsignedShort(n3 + 6)];
        n3 += 8;
        for (int i = 0; i < arrstring.length; ++i) {
            arrstring[i] = this.readClass(n3, arrc);
            n3 += 2;
        }
        String string4 = null;
        String string5 = null;
        String string6 = null;
        String string7 = null;
        String string8 = null;
        String string9 = null;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        Attribute attribute = null;
        n3 = this.a();
        for (n2 = this.readUnsignedShort((int)n3); n2 > 0; --n2) {
            int n10;
            string = this.readUTF8(n3 + 2, arrc);
            if ("SourceFile".equals(string)) {
                string5 = this.readUTF8(n3 + 8, arrc);
            } else if ("InnerClasses".equals(string)) {
                n9 = n3 + 8;
            } else if ("EnclosingMethod".equals(string)) {
                string7 = this.readClass(n3 + 8, arrc);
                n10 = this.readUnsignedShort(n3 + 10);
                if (n10 != 0) {
                    string8 = this.readUTF8(this.a[n10], arrc);
                    string9 = this.readUTF8(this.a[n10] + 2, arrc);
                }
            } else if ("Signature".equals(string)) {
                string4 = this.readUTF8(n3 + 8, arrc);
            } else if ("RuntimeVisibleAnnotations".equals(string)) {
                n5 = n3 + 8;
            } else if ("RuntimeVisibleTypeAnnotations".equals(string)) {
                n7 = n3 + 8;
            } else if ("Deprecated".equals(string)) {
                n4 |= 131072;
            } else if ("Synthetic".equals(string)) {
                n4 |= 266240;
            } else if ("SourceDebugExtension".equals(string)) {
                n10 = this.readInt(n3 + 4);
                string6 = this.a(n3 + 8, n10, new char[n10]);
            } else if ("RuntimeInvisibleAnnotations".equals(string)) {
                n6 = n3 + 8;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(string)) {
                n8 = n3 + 8;
            } else if ("BootstrapMethods".equals(string)) {
                int[] arrn = new int[this.readUnsignedShort(n3 + 8)];
                int n11 = n3 + 10;
                for (int i = 0; i < arrn.length; ++i) {
                    arrn[i] = n11;
                    n11 += 2 + this.readUnsignedShort(n11 + 2) << 1;
                }
                context.d = arrn;
            } else {
                Attribute attribute2 = this.a(arrattribute, string, n3 + 8, this.readInt(n3 + 4), arrc, -1, null);
                if (attribute2 != null) {
                    attribute2.a = attribute;
                    attribute = attribute2;
                }
            }
            n3 += 6 + this.readInt(n3 + 4);
        }
        classVisitor.visit(this.readInt(this.a[1] - 7), n4, string2, string4, string3, arrstring);
        if ((n & 2) == 0 && (string5 != null || string6 != null)) {
            classVisitor.visitSource(string5, string6);
        }
        if (string7 != null) {
            classVisitor.visitOuterClass(string7, string8, string9);
        }
        if (n5 != 0) {
            int n12 = n5 + 2;
            for (n2 = this.readUnsignedShort((int)n5); n2 > 0; --n2) {
                n12 = this.a(n12 + 2, arrc, true, classVisitor.visitAnnotation(this.readUTF8(n12, arrc), true));
            }
        }
        if (n6 != 0) {
            string = (String)(n6 + 2);
            for (n2 = this.readUnsignedShort((int)n6); n2 > 0; --n2) {
                string = (String)this.a((int)(string + 2), arrc, true, classVisitor.visitAnnotation(this.readUTF8((int)string, arrc), false));
            }
        }
        if (n7 != 0) {
            string = (String)(n7 + 2);
            for (n2 = this.readUnsignedShort((int)n7); n2 > 0; --n2) {
                string = (String)this.a(context, (int)string);
                string = (String)this.a((int)(string + 2), arrc, true, classVisitor.visitTypeAnnotation(context.i, context.j, this.readUTF8((int)string, arrc), true));
            }
        }
        if (n8 != 0) {
            string = (String)(n8 + 2);
            for (n2 = this.readUnsignedShort((int)n8); n2 > 0; --n2) {
                string = (String)this.a(context, (int)string);
                string = (String)this.a((int)(string + 2), arrc, true, classVisitor.visitTypeAnnotation(context.i, context.j, this.readUTF8((int)string, arrc), false));
            }
        }
        while (attribute != null) {
            Attribute attribute3 = attribute.a;
            attribute.a = null;
            classVisitor.visitAttribute(attribute);
            attribute = attribute3;
        }
        if (n9 != 0) {
            n2 = n9 + 2;
            for (string = (String)this.readUnsignedShort((int)n9); string > 0; --string) {
                classVisitor.visitInnerClass(this.readClass(n2, arrc), this.readClass(n2 + 2, arrc), this.readUTF8(n2 + 4, arrc), this.readUnsignedShort(n2 + 6));
                n2 += 8;
            }
        }
        n3 = this.header + 10 + 2 * arrstring.length;
        for (n2 = this.readUnsignedShort((int)(n3 - 2)); n2 > 0; --n2) {
            n3 = this.a(classVisitor, context, n3);
        }
        for (n2 = this.readUnsignedShort((int)((n3 += 2) - 2)); n2 > 0; --n2) {
            n3 = this.b(classVisitor, context, n3);
        }
        classVisitor.visitEnd();
    }

    public int readInt(int n) {
        byte[] arrby = this.b;
        return (arrby[n] & 255) << 24 | (arrby[n + 1] & 255) << 16 | (arrby[n + 2] & 255) << 8 | arrby[n + 3] & 255;
    }

    public String getClassName() {
        return this.readClass(this.header + 2, new char[this.d]);
    }

    public String readUTF8(int n, char[] arrc) {
        int n2 = this.readUnsignedShort(n);
        if (n == 0 || n2 == 0) {
            return null;
        }
        String string = this.c[n2];
        if (string != null) {
            return string;
        }
        n = this.a[n2];
        this.c[n2] = this.a(n + 2, this.readUnsignedShort(n), arrc);
        return this.c[n2];
    }

    public int getAccess() {
        return this.readUnsignedShort(this.header);
    }

    public String readClass(int n, char[] arrc) {
        return this.readUTF8(this.a[this.readUnsignedShort(n)], arrc);
    }

    public String getSuperName() {
        return this.readClass(this.header + 4, new char[this.d]);
    }

    public Object readConst(int n, char[] arrc) {
        int n2 = this.a[n];
        switch (this.b[n2 - 1]) {
            case 3: {
                return new Integer(this.readInt(n2));
            }
            case 4: {
                return new Float(Float.intBitsToFloat(this.readInt(n2)));
            }
            case 5: {
                return new Long(this.readLong(n2));
            }
            case 6: {
                return new Double(Double.longBitsToDouble(this.readLong(n2)));
            }
            case 7: {
                return Type.getObjectType((String)this.readUTF8(n2, arrc));
            }
            case 8: {
                return this.readUTF8(n2, arrc);
            }
            case 16: {
                return Type.getMethodType((String)this.readUTF8(n2, arrc));
            }
        }
        int n3 = this.readByte(n2);
        int[] arrn = this.a;
        int n4 = arrn[this.readUnsignedShort(n2 + 1)];
        boolean bl = this.b[n4 - 1] == 11;
        String string = this.readClass(n4, arrc);
        n4 = arrn[this.readUnsignedShort(n4 + 2)];
        String string2 = this.readUTF8(n4, arrc);
        String string3 = this.readUTF8(n4 + 2, arrc);
        return new Handle(n3, string, string2, string3, bl);
    }

    protected Label readLabel(int n, Label[] arrlabel) {
        if (arrlabel[n] == null) {
            arrlabel[n] = new Label();
        }
        return arrlabel[n];
    }

    public int getItemCount() {
        return this.a.length;
    }

    public int getMaxStringLength() {
        return this.d;
    }

    public int getItem(int n) {
        return this.a[n];
    }

    private int b(ClassVisitor classVisitor, Context context, int n) {
        int n2;
        String string;
        char[] arrc = context.c;
        context.e = this.readUnsignedShort(n);
        context.f = this.readUTF8(n + 2, arrc);
        context.g = this.readUTF8(n + 4, arrc);
        int n3 = 0;
        int n4 = 0;
        String[] arrstring = null;
        String string2 = null;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        int n13 = n += 6;
        String string3 = null;
        for (int i = this.readUnsignedShort((int)n); i > 0; --i) {
            string = this.readUTF8(n + 2, arrc);
            if ("Code".equals(string)) {
                if ((context.b & 1) == 0) {
                    n3 = n + 8;
                }
            } else if ("Exceptions".equals(string)) {
                arrstring = new String[this.readUnsignedShort(n + 8)];
                n4 = n + 10;
                for (n2 = 0; n2 < arrstring.length; ++n2) {
                    arrstring[n2] = this.readClass(n4, arrc);
                    n4 += 2;
                }
            } else if ("Signature".equals(string)) {
                string2 = this.readUTF8(n + 8, arrc);
            } else if ("Deprecated".equals(string)) {
                context.e |= 131072;
            } else if ("RuntimeVisibleAnnotations".equals(string)) {
                n6 = n + 8;
            } else if ("RuntimeVisibleTypeAnnotations".equals(string)) {
                n8 = n + 8;
            } else if ("AnnotationDefault".equals(string)) {
                n10 = n + 8;
            } else if ("Synthetic".equals(string)) {
                context.e |= 266240;
            } else if ("RuntimeInvisibleAnnotations".equals(string)) {
                n7 = n + 8;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(string)) {
                n9 = n + 8;
            } else if ("RuntimeVisibleParameterAnnotations".equals(string)) {
                n11 = n + 8;
            } else if ("RuntimeInvisibleParameterAnnotations".equals(string)) {
                n12 = n + 8;
            } else if ("MethodParameters".equals(string)) {
                n5 = n + 8;
            } else {
                Attribute attribute = this.a(context.a, string, n + 8, this.readInt(n + 4), arrc, -1, null);
                if (attribute != null) {
                    attribute.a = string3;
                    string3 = attribute;
                }
            }
            n += 6 + this.readInt(n + 4);
        }
        n += 2;
        MethodVisitor methodVisitor = classVisitor.visitMethod(context.e, context.f, context.g, string2, arrstring);
        if (methodVisitor == null) {
            return n;
        }
        if (methodVisitor instanceof MethodWriter) {
            string = (MethodWriter)methodVisitor;
            if (string.b.M == this && string2 == string.g) {
                n2 = 0;
                if (arrstring == null) {
                    n2 = string.j == 0 ? 1 : 0;
                } else if (arrstring.length == string.j) {
                    n2 = 1;
                    for (int i = arrstring.length - 1; i >= 0; --i) {
                        if (string.k[i] == this.readUnsignedShort(n4 -= 2)) continue;
                        n2 = 0;
                        break;
                    }
                }
                if (n2 != 0) {
                    string.h = n13;
                    string.i = n - n13;
                    return n;
                }
            }
        }
        if (n5 != 0) {
            int n14 = this.b[n5] & 255;
            n2 = n5 + 1;
            while (n14 > 0) {
                methodVisitor.visitParameter(this.readUTF8(n2, arrc), this.readUnsignedShort(n2 + 2));
                --n14;
                n2 += 4;
            }
        }
        if (n10 != 0) {
            string = methodVisitor.visitAnnotationDefault();
            this.a(n10, arrc, null, (AnnotationVisitor)string);
            if (string != null) {
                string.visitEnd();
            }
        }
        if (n6 != 0) {
            n2 = n6 + 2;
            for (int i = this.readUnsignedShort((int)n6); i > 0; --i) {
                n2 = this.a(n2 + 2, arrc, true, methodVisitor.visitAnnotation(this.readUTF8(n2, arrc), true));
            }
        }
        if (n7 != 0) {
            n2 = n7 + 2;
            for (int i = this.readUnsignedShort((int)n7); i > 0; --i) {
                n2 = this.a(n2 + 2, arrc, true, methodVisitor.visitAnnotation(this.readUTF8(n2, arrc), false));
            }
        }
        if (n8 != 0) {
            n2 = n8 + 2;
            for (int i = this.readUnsignedShort((int)n8); i > 0; --i) {
                n2 = this.a(context, n2);
                n2 = this.a(n2 + 2, arrc, true, methodVisitor.visitTypeAnnotation(context.i, context.j, this.readUTF8(n2, arrc), true));
            }
        }
        if (n9 != 0) {
            n2 = n9 + 2;
            for (int i = this.readUnsignedShort((int)n9); i > 0; --i) {
                n2 = this.a(context, n2);
                n2 = this.a(n2 + 2, arrc, true, methodVisitor.visitTypeAnnotation(context.i, context.j, this.readUTF8(n2, arrc), false));
            }
        }
        if (n11 != 0) {
            this.b(methodVisitor, context, n11, true);
        }
        if (n12 != 0) {
            this.b(methodVisitor, context, n12, false);
        }
        while (string3 != null) {
            string = string3.a;
            string3.a = null;
            methodVisitor.visitAttribute((Attribute)string3);
            string3 = string;
        }
        if (n3 != 0) {
            methodVisitor.visitCode();
            this.a(methodVisitor, context, n3);
        }
        methodVisitor.visitEnd();
        return n;
    }

    private void b(MethodVisitor methodVisitor, Context context, int n, boolean bl) {
        AnnotationVisitor annotationVisitor;
        int n2;
        int n3 = this.b[n++] & 255;
        int n4 = Type.getArgumentTypes((String)context.g).length - n3;
        for (n2 = 0; n2 < n4; ++n2) {
            annotationVisitor = methodVisitor.visitParameterAnnotation(n2, "Ljava/lang/Synthetic;", false);
            if (annotationVisitor == null) continue;
            annotationVisitor.visitEnd();
        }
        char[] arrc = context.c;
        while (n2 < n3 + n4) {
            int n5 = this.readUnsignedShort(n);
            n += 2;
            while (n5 > 0) {
                annotationVisitor = methodVisitor.visitParameterAnnotation(n2, this.readUTF8(n, arrc), bl);
                n = this.a(n + 2, arrc, true, annotationVisitor);
                --n5;
            }
            ++n2;
        }
    }

    public int readByte(int n) {
        return this.b[n] & 255;
    }

    public short readShort(int n) {
        byte[] arrby = this.b;
        return (short)((arrby[n] & 255) << 8 | arrby[n + 1] & 255);
    }

    public long readLong(int n) {
        long l = this.readInt(n);
        long l2 = (long)this.readInt(n + 4) & 0xFFFFFFFFL;
        return l << 32 | l2;
    }

    public int readUnsignedShort(int n) {
        byte[] arrby = this.b;
        return (arrby[n] & 255) << 8 | arrby[n + 1] & 255;
    }

    void a(ClassWriter classWriter) {
        int n;
        char[] arrc = new char[this.d];
        int n2 = this.a.length;
        Item[] arritem = new Item[n2];
        for (n = 1; n < n2; ++n) {
            int n3;
            int n4 = this.a[n];
            byte by = this.b[n4 - 1];
            Item item = new Item(n);
            switch (by) {
                int n5;
                case 9: 
                case 10: 
                case 11: {
                    n5 = this.a[this.readUnsignedShort(n4 + 2)];
                    item.a((int)by, this.readClass(n4, arrc), this.readUTF8(n5, arrc), this.readUTF8(n5 + 2, arrc));
                    break;
                }
                case 3: {
                    item.a(this.readInt(n4));
                    break;
                }
                case 4: {
                    item.a(Float.intBitsToFloat(this.readInt(n4)));
                    break;
                }
                case 12: {
                    item.a((int)by, this.readUTF8(n4, arrc), this.readUTF8(n4 + 2, arrc), null);
                    break;
                }
                case 5: {
                    item.a(this.readLong(n4));
                    ++n;
                    break;
                }
                case 6: {
                    item.a(Double.longBitsToDouble(this.readLong(n4)));
                    ++n;
                    break;
                }
                case 1: {
                    String string = this.c[n];
                    if (string == null) {
                        n4 = this.a[n];
                        string = this.c[n] = this.a(n4 + 2, this.readUnsignedShort(n4), arrc);
                    }
                    item.a((int)by, string, null, null);
                    break;
                }
                case 15: {
                    n3 = this.a[this.readUnsignedShort(n4 + 1)];
                    n5 = this.a[this.readUnsignedShort(n3 + 2)];
                    item.a(20 + this.readByte(n4), this.readClass(n3, arrc), this.readUTF8(n5, arrc), this.readUTF8(n5 + 2, arrc));
                    break;
                }
                case 18: {
                    if (classWriter.A == null) {
                        this.a(classWriter, arritem, arrc);
                    }
                    n5 = this.a[this.readUnsignedShort(n4 + 2)];
                    item.a(this.readUTF8(n5, arrc), this.readUTF8(n5 + 2, arrc), this.readUnsignedShort(n4));
                    break;
                }
                default: {
                    item.a((int)by, this.readUTF8(n4, arrc), null, null);
                }
            }
            n3 = item.j % arritem.length;
            item.k = arritem[n3];
            arritem[n3] = item;
        }
        n = this.a[1] - 1;
        classWriter.d.putByteArray(this.b, n, this.header - n);
        classWriter.e = arritem;
        classWriter.f = (int)(0.0 * (double)n2);
        classWriter.c = n2;
    }

    private int a(int n, char[] arrc, boolean bl, AnnotationVisitor annotationVisitor) {
        int n2 = this.readUnsignedShort(n);
        n += 2;
        if (bl) {
            while (n2 > 0) {
                n = this.a(n + 2, arrc, this.readUTF8(n, arrc), annotationVisitor);
                --n2;
            }
        } else {
            while (n2 > 0) {
                n = this.a(n, arrc, null, annotationVisitor);
                --n2;
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return n;
    }

    private static byte[] a(InputStream inputStream, boolean bl) throws IOException {
        if (inputStream == null) {
            throw new IOException("Class not found");
        }
        try {
            byte[] arrby = new byte[inputStream.available()];
            int n = 0;
            do {
                byte[] arrby2;
                int n2;
                if ((n2 = inputStream.read(arrby, n, arrby.length - n)) == -1) {
                    byte[] arrby3;
                    if (n < arrby.length) {
                        arrby3 = new byte[n];
                        System.arraycopy(arrby, 0, arrby3, 0, n);
                        arrby = arrby3;
                    }
                    arrby3 = arrby;
                    return arrby3;
                }
                if ((n += n2) != arrby.length) continue;
                int n3 = inputStream.read();
                if (n3 < 0) {
                    arrby2 = arrby;
                    return arrby2;
                }
                arrby2 = new byte[arrby.length + 1000];
                System.arraycopy(arrby, 0, arrby2, 0, n);
                arrby2[n++] = (byte)n3;
                arrby = arrby2;
            } while (true);
        }
        finally {
            if (bl) {
                inputStream.close();
            }
        }
    }

    private String a(int n, int n2, char[] arrc) {
        int n3 = n + n2;
        byte[] arrby = this.b;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        while (n < n3) {
            int n7 = arrby[n++];
            switch (n5) {
                case 0: {
                    if ((n7 &= 255) < 128) {
                        arrc[n4++] = (char)n7;
                        break;
                    }
                    if (n7 < 224 && n7 > 191) {
                        n6 = (char)(n7 & 31);
                        n5 = 1;
                        break;
                    }
                    n6 = (char)(n7 & 15);
                    n5 = 2;
                    break;
                }
                case 1: {
                    arrc[n4++] = (char)(n6 << 6 | n7 & 63);
                    n5 = 0;
                    break;
                }
                case 2: {
                    n6 = (char)(n6 << 6 | n7 & 63);
                    n5 = 1;
                }
            }
        }
        return new String(arrc, 0, n4);
    }

    private void a(ClassWriter classWriter, Item[] arritem, char[] arrc) {
        int n;
        int n2;
        int n3 = this.a();
        boolean bl = false;
        for (n2 = this.readUnsignedShort((int)n3); n2 > 0; --n2) {
            String string = this.readUTF8(n3 + 2, arrc);
            if ("BootstrapMethods".equals(string)) {
                bl = true;
                break;
            }
            n3 += 6 + this.readInt(n3 + 4);
        }
        if (!bl) {
            return;
        }
        n2 = this.readUnsignedShort(n3 + 8);
        int n4 = n3 + 10;
        for (n = 0; n < n2; ++n) {
            int n5 = n4 - n3 - 10;
            int n6 = this.readConst(this.readUnsignedShort(n4), arrc).hashCode();
            for (int i = this.readUnsignedShort((int)(n4 + 2)); i > 0; --i) {
                n6 ^= this.readConst(this.readUnsignedShort(n4 + 4), arrc).hashCode();
                n4 += 2;
            }
            n4 += 4;
            Item item = new Item(n);
            item.a(n5, n6 & Integer.MAX_VALUE);
            int n7 = item.j % arritem.length;
            item.k = arritem[n7];
            arritem[n7] = item;
        }
        n = this.readInt(n3 + 4);
        ByteVector byteVector = new ByteVector(n + 62);
        byteVector.putByteArray(this.b, n3 + 10, n - 2);
        classWriter.z = n2;
        classWriter.A = byteVector;
    }

    private int a(int n, char[] arrc, String string, AnnotationVisitor annotationVisitor) {
        if (annotationVisitor == null) {
            switch (this.b[n] & 255) {
                case 101: {
                    return n + 5;
                }
                case 64: {
                    return this.a(n + 3, arrc, true, null);
                }
                case 91: {
                    return this.a(n + 1, arrc, false, null);
                }
            }
            return n + 3;
        }
        block5 : switch (this.b[n++] & 255) {
            case 68: 
            case 70: 
            case 73: 
            case 74: {
                annotationVisitor.visit(string, this.readConst(this.readUnsignedShort(n), arrc));
                n += 2;
                break;
            }
            case 66: {
                annotationVisitor.visit(string, (Object)new Byte((byte)this.readInt(this.a[this.readUnsignedShort(n)])));
                n += 2;
                break;
            }
            case 90: {
                annotationVisitor.visit(string, (Object)(this.readInt(this.a[this.readUnsignedShort(n)]) == 0 ? Boolean.FALSE : Boolean.TRUE));
                n += 2;
                break;
            }
            case 83: {
                annotationVisitor.visit(string, (Object)new Short((short)this.readInt(this.a[this.readUnsignedShort(n)])));
                n += 2;
                break;
            }
            case 67: {
                annotationVisitor.visit(string, (Object)new Character((char)this.readInt(this.a[this.readUnsignedShort(n)])));
                n += 2;
                break;
            }
            case 115: {
                annotationVisitor.visit(string, (Object)this.readUTF8(n, arrc));
                n += 2;
                break;
            }
            case 101: {
                annotationVisitor.visitEnum(string, this.readUTF8(n, arrc), this.readUTF8(n + 2, arrc));
                n += 4;
                break;
            }
            case 99: {
                annotationVisitor.visit(string, (Object)Type.getType((String)this.readUTF8(n, arrc)));
                n += 2;
                break;
            }
            case 64: {
                n = this.a(n + 2, arrc, true, annotationVisitor.visitAnnotation(string, this.readUTF8(n, arrc)));
                break;
            }
            case 91: {
                int n2 = this.readUnsignedShort(n);
                n += 2;
                if (n2 == 0) {
                    return this.a(n - 2, arrc, false, annotationVisitor.visitArray(string));
                }
                switch (this.b[n++] & 255) {
                    case 66: {
                        byte[] arrby = new byte[n2];
                        for (int i = 0; i < n2; ++i) {
                            arrby[i] = (byte)this.readInt(this.a[this.readUnsignedShort(n)]);
                            n += 3;
                        }
                        annotationVisitor.visit(string, (Object)arrby);
                        --n;
                        break block5;
                    }
                    case 90: {
                        boolean[] arrbl = new boolean[n2];
                        for (int i = 0; i < n2; ++i) {
                            arrbl[i] = this.readInt(this.a[this.readUnsignedShort(n)]) != 0;
                            n += 3;
                        }
                        annotationVisitor.visit(string, (Object)arrbl);
                        --n;
                        break block5;
                    }
                    case 83: {
                        short[] arrs = new short[n2];
                        for (int i = 0; i < n2; ++i) {
                            arrs[i] = (short)this.readInt(this.a[this.readUnsignedShort(n)]);
                            n += 3;
                        }
                        annotationVisitor.visit(string, (Object)arrs);
                        --n;
                        break block5;
                    }
                    case 67: {
                        char[] arrc2 = new char[n2];
                        for (int i = 0; i < n2; ++i) {
                            arrc2[i] = (char)this.readInt(this.a[this.readUnsignedShort(n)]);
                            n += 3;
                        }
                        annotationVisitor.visit(string, (Object)arrc2);
                        --n;
                        break block5;
                    }
                    case 73: {
                        int[] arrn = new int[n2];
                        for (int i = 0; i < n2; ++i) {
                            arrn[i] = this.readInt(this.a[this.readUnsignedShort(n)]);
                            n += 3;
                        }
                        annotationVisitor.visit(string, (Object)arrn);
                        --n;
                        break block5;
                    }
                    case 74: {
                        long[] arrl = new long[n2];
                        for (int i = 0; i < n2; ++i) {
                            arrl[i] = this.readLong(this.a[this.readUnsignedShort(n)]);
                            n += 3;
                        }
                        annotationVisitor.visit(string, (Object)arrl);
                        --n;
                        break block5;
                    }
                    case 70: {
                        float[] arrf = new float[n2];
                        for (int i = 0; i < n2; ++i) {
                            arrf[i] = Float.intBitsToFloat(this.readInt(this.a[this.readUnsignedShort(n)]));
                            n += 3;
                        }
                        annotationVisitor.visit(string, (Object)arrf);
                        --n;
                        break block5;
                    }
                    case 68: {
                        double[] arrd = new double[n2];
                        for (int i = 0; i < n2; ++i) {
                            arrd[i] = Double.longBitsToDouble(this.readLong(this.a[this.readUnsignedShort(n)]));
                            n += 3;
                        }
                        annotationVisitor.visit(string, (Object)arrd);
                        --n;
                        break block5;
                    }
                }
                n = this.a(n - 3, arrc, false, annotationVisitor.visitArray(string));
            }
        }
        return n;
    }

    private int a(ClassVisitor classVisitor, Context context, int n) {
        String string;
        int n2;
        char[] arrc = context.c;
        int n3 = this.readUnsignedShort(n);
        String string2 = this.readUTF8(n + 2, arrc);
        String string3 = this.readUTF8(n + 4, arrc);
        String string4 = null;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        Object object = null;
        Attribute attribute = null;
        for (int i = this.readUnsignedShort((int)(n += 6)); i > 0; --i) {
            string = this.readUTF8(n + 2, arrc);
            if ("ConstantValue".equals(string)) {
                n2 = this.readUnsignedShort(n + 8);
                object = n2 == 0 ? null : this.readConst(n2, arrc);
            } else if ("Signature".equals(string)) {
                string4 = this.readUTF8(n + 8, arrc);
            } else if ("Deprecated".equals(string)) {
                n3 |= 131072;
            } else if ("Synthetic".equals(string)) {
                n3 |= 266240;
            } else if ("RuntimeVisibleAnnotations".equals(string)) {
                n4 = n + 8;
            } else if ("RuntimeVisibleTypeAnnotations".equals(string)) {
                n6 = n + 8;
            } else if ("RuntimeInvisibleAnnotations".equals(string)) {
                n5 = n + 8;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(string)) {
                n7 = n + 8;
            } else {
                Attribute attribute2 = this.a(context.a, string, n + 8, this.readInt(n + 4), arrc, -1, null);
                if (attribute2 != null) {
                    attribute2.a = attribute;
                    attribute = attribute2;
                }
            }
            n += 6 + this.readInt(n + 4);
        }
        n += 2;
        FieldVisitor fieldVisitor = classVisitor.visitField(n3, string2, string3, string4, object);
        if (fieldVisitor == null) {
            return n;
        }
        if (n4 != 0) {
            n2 = n4 + 2;
            for (int i = this.readUnsignedShort((int)n4); i > 0; --i) {
                n2 = this.a(n2 + 2, arrc, true, fieldVisitor.visitAnnotation(this.readUTF8(n2, arrc), true));
            }
        }
        if (n5 != 0) {
            n2 = n5 + 2;
            for (string = (String)this.readUnsignedShort((int)n5); string > 0; --string) {
                n2 = this.a(n2 + 2, arrc, true, fieldVisitor.visitAnnotation(this.readUTF8(n2, arrc), false));
            }
        }
        if (n6 != 0) {
            n2 = n6 + 2;
            for (string = (String)this.readUnsignedShort((int)n6); string > 0; --string) {
                n2 = this.a(context, n2);
                n2 = this.a(n2 + 2, arrc, true, fieldVisitor.visitTypeAnnotation(context.i, context.j, this.readUTF8(n2, arrc), true));
            }
        }
        if (n7 != 0) {
            n2 = n7 + 2;
            for (string = (String)this.readUnsignedShort((int)n7); string > 0; --string) {
                n2 = this.a(context, n2);
                n2 = this.a(n2 + 2, arrc, true, fieldVisitor.visitTypeAnnotation(context.i, context.j, this.readUTF8(n2, arrc), false));
            }
        }
        while (attribute != null) {
            Attribute attribute3 = attribute.a;
            attribute.a = null;
            fieldVisitor.visitAttribute(attribute);
            attribute = attribute3;
        }
        fieldVisitor.visitEnd();
        return n;
    }

    private int a(Context context, int n) {
        int n2;
        int n3 = this.readInt(n);
        switch (n3 >>> 24) {
            case 0: 
            case 1: 
            case 22: {
                n3 &= -65536;
                n += 2;
                break;
            }
            case 19: 
            case 20: 
            case 21: {
                n3 &= -16777216;
                ++n;
                break;
            }
            case 64: 
            case 65: {
                n3 &= -16777216;
                n2 = this.readUnsignedShort(n + 1);
                context.l = new Label[n2];
                context.m = new Label[n2];
                context.n = new int[n2];
                n += 3;
                for (int i = 0; i < n2; ++i) {
                    int n4 = this.readUnsignedShort(n);
                    int n5 = this.readUnsignedShort(n + 2);
                    context.l[i] = this.readLabel(n4, context.h);
                    context.m[i] = this.readLabel(n4 + n5, context.h);
                    context.n[i] = this.readUnsignedShort(n + 4);
                    n += 6;
                }
                break;
            }
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 75: {
                n3 &= -16776961;
                n += 4;
                break;
            }
            default: {
                n3 &= n3 >>> 24 < 67 ? -256 : -16777216;
                n += 3;
            }
        }
        n2 = this.readByte(n);
        context.i = n3;
        context.j = n2 == 0 ? null : new TypePath(this.b, n);
        return n + 1 + 2 * n2;
    }

    private int[] a(MethodVisitor methodVisitor, Context context, int n, boolean bl) {
        char[] arrc = context.c;
        int[] arrn = new int[this.readUnsignedShort(n)];
        n += 2;
        for (int i = 0; i < arrn.length; ++i) {
            Object object;
            int n2;
            arrn[i] = n;
            int n3 = this.readInt(n);
            switch (n3 >>> 24) {
                case 0: 
                case 1: 
                case 22: {
                    n += 2;
                    break;
                }
                case 19: 
                case 20: 
                case 21: {
                    ++n;
                    break;
                }
                case 64: 
                case 65: {
                    for (n2 = this.readUnsignedShort((int)(n + 1)); n2 > 0; --n2) {
                        object = this.readUnsignedShort(n + 3);
                        int n4 = this.readUnsignedShort(n + 5);
                        this.readLabel((int)object, context.h);
                        this.readLabel((int)(object + n4), context.h);
                        n += 6;
                    }
                    n += 3;
                    break;
                }
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: {
                    n += 4;
                    break;
                }
                default: {
                    n += 3;
                }
            }
            n2 = this.readByte(n);
            if (n3 >>> 24 == 66) {
                object = (Object)(n2 == 0 ? null : new TypePath(this.b, n));
                n += 1 + 2 * n2;
                n = this.a(n + 2, arrc, true, methodVisitor.visitTryCatchAnnotation(n3, (TypePath)object, this.readUTF8(n, arrc), bl));
                continue;
            }
            n = this.a(n + 3 + 2 * n2, arrc, true, null);
        }
        return arrn;
    }

    private void a(MethodVisitor methodVisitor, Context context, int n) {
        int n2;
        String string;
        int n3;
        int n4;
        int n5;
        int n6;
        byte[] arrby = this.b;
        char[] arrc = context.c;
        int n7 = this.readUnsignedShort(n);
        int n8 = this.readUnsignedShort(n + 2);
        int n9 = this.readInt(n + 4);
        int n10 = n += 8;
        int n11 = n + n9;
        context.h = new Label[n9 + 2];
        Label[] arrlabel = context.h;
        this.readLabel(n9 + 1, arrlabel);
        block29 : while (n < n11) {
            n6 = n - n10;
            int n12 = arrby[n] & 255;
            switch (ClassWriter.a[n12]) {
                int n13;
                case 0: 
                case 4: {
                    ++n;
                    continue block29;
                }
                case 9: {
                    this.readLabel(n6 + this.readShort(n + 1), arrlabel);
                    n += 3;
                    continue block29;
                }
                case 10: {
                    this.readLabel(n6 + this.readInt(n + 1), arrlabel);
                    n += 5;
                    continue block29;
                }
                case 17: {
                    n12 = arrby[n + 1] & 255;
                    if (n12 == 132) {
                        n += 6;
                        continue block29;
                    }
                    n += 4;
                    continue block29;
                }
                case 14: {
                    n = n + 4 - (n6 & 3);
                    this.readLabel(n6 + this.readInt(n), arrlabel);
                    for (n13 = this.readInt((int)(n + 8)) - this.readInt((int)(n + 4)) + 1; n13 > 0; --n13) {
                        this.readLabel(n6 + this.readInt(n + 12), arrlabel);
                        n += 4;
                    }
                    n += 12;
                    continue block29;
                }
                case 15: {
                    n = n + 4 - (n6 & 3);
                    this.readLabel(n6 + this.readInt(n), arrlabel);
                    for (n13 = this.readInt((int)(n + 4)); n13 > 0; --n13) {
                        this.readLabel(n6 + this.readInt(n + 12), arrlabel);
                        n += 8;
                    }
                    n += 8;
                    continue block29;
                }
                case 1: 
                case 3: 
                case 11: {
                    n += 2;
                    continue block29;
                }
                case 2: 
                case 5: 
                case 6: 
                case 12: 
                case 13: {
                    n += 3;
                    continue block29;
                }
                case 7: 
                case 8: {
                    n += 5;
                    continue block29;
                }
            }
            n += 4;
        }
        for (n6 = this.readUnsignedShort((int)n); n6 > 0; --n6) {
            Label label = this.readLabel(this.readUnsignedShort(n + 2), arrlabel);
            Label label2 = this.readLabel(this.readUnsignedShort(n + 4), arrlabel);
            Label label3 = this.readLabel(this.readUnsignedShort(n + 6), arrlabel);
            String string2 = this.readUTF8(this.a[this.readUnsignedShort(n + 8)], arrc);
            methodVisitor.visitTryCatchBlock(label, label2, label3, string2);
            n += 8;
        }
        int[] arrn = null;
        int[] arrn2 = null;
        int n14 = 0;
        int n15 = 0;
        int n16 = -1;
        int n17 = -1;
        int n18 = 0;
        int n19 = 0;
        boolean bl = true;
        boolean bl2 = (context.b & 8) != 0;
        int n20 = 0;
        int n21 = 0;
        int n22 = 0;
        Context context2 = null;
        Attribute attribute = null;
        for (n2 = this.readUnsignedShort((int)(n += 2)); n2 > 0; --n2) {
            string = this.readUTF8(n + 2, arrc);
            if ("LocalVariableTable".equals(string)) {
                if ((context.b & 2) == 0) {
                    n18 = n + 8;
                    n3 = n;
                    for (n5 = this.readUnsignedShort((int)(n + 8)); n5 > 0; --n5) {
                        n4 = this.readUnsignedShort(n3 + 10);
                        if (arrlabel[n4] == null) {
                            this.readLabel((int)n4, (Label[])arrlabel).a |= 1;
                        }
                        if (arrlabel[n4 += this.readUnsignedShort(n3 + 12)] == null) {
                            this.readLabel((int)n4, (Label[])arrlabel).a |= 1;
                        }
                        n3 += 10;
                    }
                }
            } else if ("LocalVariableTypeTable".equals(string)) {
                n19 = n + 8;
            } else if ("LineNumberTable".equals(string)) {
                if ((context.b & 2) == 0) {
                    n3 = n;
                    for (n5 = this.readUnsignedShort((int)(n + 8)); n5 > 0; --n5) {
                        n4 = this.readUnsignedShort(n3 + 10);
                        if (arrlabel[n4] == null) {
                            this.readLabel((int)n4, (Label[])arrlabel).a |= 1;
                        }
                        Label label = arrlabel[n4];
                        while (label.b > 0) {
                            if (label.k == null) {
                                label.k = new Label();
                            }
                            label = label.k;
                        }
                        label.b = this.readUnsignedShort(n3 + 12);
                        n3 += 4;
                    }
                }
            } else if ("RuntimeVisibleTypeAnnotations".equals(string)) {
                arrn = this.a(methodVisitor, context, n + 8, true);
                n16 = arrn.length == 0 || this.readByte(arrn[0]) < 67 ? -1 : this.readUnsignedShort(arrn[0] + 1);
            } else if ("RuntimeInvisibleTypeAnnotations".equals(string)) {
                arrn2 = this.a(methodVisitor, context, n + 8, false);
                n17 = arrn2.length == 0 || this.readByte(arrn2[0]) < 67 ? -1 : this.readUnsignedShort(arrn2[0] + 1);
            } else if ("StackMapTable".equals(string)) {
                if ((context.b & 4) == 0) {
                    n20 = n + 10;
                    n21 = this.readInt(n + 4);
                    n22 = this.readUnsignedShort(n + 8);
                }
            } else if ("StackMap".equals(string)) {
                if ((context.b & 4) == 0) {
                    bl = false;
                    n20 = n + 10;
                    n21 = this.readInt(n + 4);
                    n22 = this.readUnsignedShort(n + 8);
                }
            } else {
                for (n5 = 0; n5 < context.a.length; ++n5) {
                    Attribute attribute2;
                    if (!context.a[n5].type.equals(string) || (attribute2 = context.a[n5].read(this, n + 8, this.readInt(n + 4), arrc, n10 - 8, arrlabel)) == null) continue;
                    attribute2.a = attribute;
                    attribute = attribute2;
                }
            }
            n += 6 + this.readInt(n + 4);
        }
        n += 2;
        if (n20 != 0) {
            context2 = context;
            context2.o = -1;
            context2.p = 0;
            context2.q = 0;
            context2.r = 0;
            context2.t = 0;
            context2.s = new Object[n8];
            context2.u = new Object[n7];
            if (bl2) {
                this.a(context);
            }
            for (n2 = n20; n2 < n20 + n21 - 2; ++n2) {
                int n23;
                if (arrby[n2] != 8 || (n23 = this.readUnsignedShort(n2 + 1)) < 0 || n23 >= n9 || (arrby[n10 + n23] & 255) != 187) continue;
                this.readLabel(n23, arrlabel);
            }
        }
        n = n10;
        while (n < n11) {
            n2 = n - n10;
            string = arrlabel[n2];
            if (string != null) {
                Label label = string.k;
                string.k = null;
                methodVisitor.visitLabel((Label)string);
                if ((context.b & 2) == 0 && string.b > 0) {
                    methodVisitor.visitLineNumber(string.b, (Label)string);
                    while (label != null) {
                        methodVisitor.visitLineNumber(label.b, (Label)string);
                        label = label.k;
                    }
                }
            }
            while (context2 != null && (context2.o == n2 || context2.o == -1)) {
                if (context2.o != -1) {
                    if (!bl || bl2) {
                        methodVisitor.visitFrame(-1, context2.q, context2.s, context2.t, context2.u);
                    } else {
                        methodVisitor.visitFrame(context2.p, context2.r, context2.s, context2.t, context2.u);
                    }
                }
                if (n22 > 0) {
                    n20 = this.a(n20, bl, bl2, context2);
                    --n22;
                    continue;
                }
                context2 = null;
            }
            n5 = arrby[n] & 255;
            switch (ClassWriter.a[n5]) {
                Object object;
                int n24;
                case 0: {
                    methodVisitor.visitInsn(n5);
                    ++n;
                    break;
                }
                case 4: {
                    if (n5 > 54) {
                        methodVisitor.visitVarInsn(54 + (n5 >> 2), (n5 -= 59) & 3);
                    } else {
                        methodVisitor.visitVarInsn(21 + (n5 >> 2), (n5 -= 26) & 3);
                    }
                    ++n;
                    break;
                }
                case 9: {
                    methodVisitor.visitJumpInsn(n5, arrlabel[n2 + this.readShort(n + 1)]);
                    n += 3;
                    break;
                }
                case 10: {
                    methodVisitor.visitJumpInsn(n5 - 33, arrlabel[n2 + this.readInt(n + 1)]);
                    n += 5;
                    break;
                }
                case 17: {
                    n5 = arrby[n + 1] & 255;
                    if (n5 == 132) {
                        methodVisitor.visitIincInsn(this.readUnsignedShort(n + 2), (int)this.readShort(n + 4));
                        n += 6;
                        break;
                    }
                    methodVisitor.visitVarInsn(n5, this.readUnsignedShort(n + 2));
                    n += 4;
                    break;
                }
                case 14: {
                    n = n + 4 - (n2 & 3);
                    n3 = n2 + this.readInt(n);
                    n4 = this.readInt(n + 4);
                    int n25 = this.readInt(n + 8);
                    object = new Label[n25 - n4 + 1];
                    n += 12;
                    for (n24 = 0; n24 < object.length; n24 += 1) {
                        object[n24] = arrlabel[n2 + this.readInt(n)];
                        n += 4;
                    }
                    methodVisitor.visitTableSwitchInsn(n4, n25, arrlabel[n3], (Label[])object);
                    break;
                }
                case 15: {
                    n = n + 4 - (n2 & 3);
                    n3 = n2 + this.readInt(n);
                    n4 = this.readInt(n + 4);
                    int[] arrn3 = new int[n4];
                    object = new Label[n4];
                    n += 8;
                    for (n24 = 0; n24 < n4; n24 += 1) {
                        arrn3[n24] = this.readInt(n);
                        object[n24] = arrlabel[n2 + this.readInt(n + 4)];
                        n += 8;
                    }
                    methodVisitor.visitLookupSwitchInsn(arrlabel[n3], arrn3, (Label[])object);
                    break;
                }
                case 3: {
                    methodVisitor.visitVarInsn(n5, arrby[n + 1] & 255);
                    n += 2;
                    break;
                }
                case 1: {
                    methodVisitor.visitIntInsn(n5, (int)arrby[n + 1]);
                    n += 2;
                    break;
                }
                case 2: {
                    methodVisitor.visitIntInsn(n5, (int)this.readShort(n + 1));
                    n += 3;
                    break;
                }
                case 11: {
                    methodVisitor.visitLdcInsn(this.readConst(arrby[n + 1] & 255, arrc));
                    n += 2;
                    break;
                }
                case 12: {
                    methodVisitor.visitLdcInsn(this.readConst(this.readUnsignedShort(n + 1), arrc));
                    n += 3;
                    break;
                }
                case 6: 
                case 7: {
                    n3 = this.a[this.readUnsignedShort(n + 1)];
                    n4 = arrby[n3 - 1] == 11 ? 1 : 0;
                    String string3 = this.readClass(n3, arrc);
                    n3 = this.a[this.readUnsignedShort(n3 + 2)];
                    object = this.readUTF8(n3, arrc);
                    String string4 = this.readUTF8(n3 + 2, arrc);
                    if (n5 < 182) {
                        methodVisitor.visitFieldInsn(n5, string3, (String)object, string4);
                    } else {
                        methodVisitor.visitMethodInsn(n5, string3, (String)object, string4, (boolean)n4);
                    }
                    if (n5 == 185) {
                        n += 5;
                        break;
                    }
                    n += 3;
                    break;
                }
                case 8: {
                    n3 = this.a[this.readUnsignedShort(n + 1)];
                    n4 = context.d[this.readUnsignedShort(n3)];
                    Handle handle = (Handle)this.readConst(this.readUnsignedShort(n4), arrc);
                    int n26 = this.readUnsignedShort(n4 + 2);
                    Object[] arrobject = new Object[n26];
                    n4 += 4;
                    for (int i = 0; i < n26; ++i) {
                        arrobject[i] = this.readConst(this.readUnsignedShort(n4), arrc);
                        n4 += 2;
                    }
                    n3 = this.a[this.readUnsignedShort(n3 + 2)];
                    String string5 = this.readUTF8(n3, arrc);
                    String string6 = this.readUTF8(n3 + 2, arrc);
                    methodVisitor.visitInvokeDynamicInsn(string5, string6, handle, arrobject);
                    n += 5;
                    break;
                }
                case 5: {
                    methodVisitor.visitTypeInsn(n5, this.readClass(n + 1, arrc));
                    n += 3;
                    break;
                }
                case 13: {
                    methodVisitor.visitIincInsn(arrby[n + 1] & 255, (int)arrby[n + 2]);
                    n += 3;
                    break;
                }
                default: {
                    methodVisitor.visitMultiANewArrayInsn(this.readClass(n + 1, arrc), arrby[n + 3] & 255);
                    n += 4;
                }
            }
            while (arrn != null && n14 < arrn.length && n16 <= n2) {
                if (n16 == n2) {
                    n3 = this.a(context, arrn[n14]);
                    this.a(n3 + 2, arrc, true, methodVisitor.visitInsnAnnotation(context.i, context.j, this.readUTF8(n3, arrc), true));
                }
                n16 = ++n14 >= arrn.length || this.readByte(arrn[n14]) < 67 ? -1 : this.readUnsignedShort(arrn[n14] + 1);
            }
            while (arrn2 != null && n15 < arrn2.length && n17 <= n2) {
                if (n17 == n2) {
                    n3 = this.a(context, arrn2[n15]);
                    this.a(n3 + 2, arrc, true, methodVisitor.visitInsnAnnotation(context.i, context.j, this.readUTF8(n3, arrc), false));
                }
                n17 = ++n15 >= arrn2.length || this.readByte(arrn2[n15]) < 67 ? -1 : this.readUnsignedShort(arrn2[n15] + 1);
            }
        }
        if (arrlabel[n9] != null) {
            methodVisitor.visitLabel(arrlabel[n9]);
        }
        if ((context.b & 2) == 0 && n18 != 0) {
            int[] arrn4 = null;
            if (n19 != 0) {
                n = n19 + 2;
                arrn4 = new int[this.readUnsignedShort(n19) * 3];
                int n27 = arrn4.length;
                while (n27 > 0) {
                    arrn4[--n27] = n + 6;
                    arrn4[--n27] = this.readUnsignedShort(n + 8);
                    arrn4[--n27] = this.readUnsignedShort(n);
                    n += 10;
                }
            }
            n = n18 + 2;
            for (string = (String)this.readUnsignedShort((int)n18); string > 0; --string) {
                n5 = this.readUnsignedShort(n);
                n3 = this.readUnsignedShort(n + 2);
                n4 = this.readUnsignedShort(n + 8);
                String string7 = null;
                if (arrn4 != null) {
                    for (int i = 0; i < arrn4.length; i += 3) {
                        if (arrn4[i] != n5 || arrn4[i + 1] != n4) continue;
                        string7 = this.readUTF8(arrn4[i + 2], arrc);
                        break;
                    }
                }
                methodVisitor.visitLocalVariable(this.readUTF8(n + 4, arrc), this.readUTF8(n + 6, arrc), string7, arrlabel[n5], arrlabel[n5 + n3], n4);
                n += 10;
            }
        }
        if (arrn != null) {
            for (n2 = 0; n2 < arrn.length; ++n2) {
                if (this.readByte((int)arrn[n2]) >> 1 != 32) continue;
                string = (String)this.a(context, arrn[n2]);
                string = (String)this.a((int)(string + 2), arrc, true, methodVisitor.visitLocalVariableAnnotation(context.i, context.j, context.l, context.m, context.n, this.readUTF8((int)string, arrc), true));
            }
        }
        if (arrn2 != null) {
            for (n2 = 0; n2 < arrn2.length; ++n2) {
                if (this.readByte((int)arrn2[n2]) >> 1 != 32) continue;
                string = (String)this.a(context, arrn2[n2]);
                string = (String)this.a((int)(string + 2), arrc, true, methodVisitor.visitLocalVariableAnnotation(context.i, context.j, context.l, context.m, context.n, this.readUTF8((int)string, arrc), false));
            }
        }
        while (attribute != null) {
            Attribute attribute3 = attribute.a;
            attribute.a = null;
            methodVisitor.visitAttribute(attribute);
            attribute = attribute3;
        }
        methodVisitor.visitMaxs(n7, n8);
    }

    private Attribute a(Attribute[] arrattribute, String string, int n, int n2, char[] arrc, int n3, Label[] arrlabel) {
        for (int i = 0; i < arrattribute.length; ++i) {
            if (!arrattribute[i].type.equals(string)) continue;
            return arrattribute[i].read(this, n, n2, arrc, n3, arrlabel);
        }
        return new Attribute(string).read(this, n, n2, null, -1, null);
    }

    private int a() {
        int n;
        int n2;
        int n3 = this.header + 8 + this.readUnsignedShort(this.header + 6) * 2;
        for (n2 = this.readUnsignedShort((int)n3); n2 > 0; --n2) {
            for (n = this.readUnsignedShort((int)(n3 + 8)); n > 0; --n) {
                n3 += 6 + this.readInt(n3 + 12);
            }
            n3 += 8;
        }
        for (n2 = this.readUnsignedShort((int)(n3 += 2)); n2 > 0; --n2) {
            for (n = this.readUnsignedShort((int)(n3 + 8)); n > 0; --n) {
                n3 += 6 + this.readInt(n3 + 12);
            }
            n3 += 8;
        }
        return n3 + 2;
    }

    private int a(Object[] arrobject, int n, int n2, char[] arrc, Label[] arrlabel) {
        int n3 = this.b[n2++] & 255;
        switch (n3) {
            case 0: {
                arrobject[n] = Opcodes.TOP;
                break;
            }
            case 1: {
                arrobject[n] = Opcodes.INTEGER;
                break;
            }
            case 2: {
                arrobject[n] = Opcodes.FLOAT;
                break;
            }
            case 3: {
                arrobject[n] = Opcodes.DOUBLE;
                break;
            }
            case 4: {
                arrobject[n] = Opcodes.LONG;
                break;
            }
            case 5: {
                arrobject[n] = Opcodes.NULL;
                break;
            }
            case 6: {
                arrobject[n] = Opcodes.UNINITIALIZED_THIS;
                break;
            }
            case 7: {
                arrobject[n] = this.readClass(n2, arrc);
                n2 += 2;
                break;
            }
            default: {
                arrobject[n] = this.readLabel(this.readUnsignedShort(n2), arrlabel);
                n2 += 2;
            }
        }
        return n2;
    }

    private int a(int n, boolean bl, boolean bl2, Context context) {
        int n2;
        int n3;
        char[] arrc = context.c;
        Label[] arrlabel = context.h;
        if (bl) {
            n2 = this.b[n++] & 255;
        } else {
            n2 = 255;
            context.o = -1;
        }
        context.r = 0;
        if (n2 < 64) {
            n3 = n2;
            context.p = 3;
            context.t = 0;
        } else if (n2 < 128) {
            n3 = n2 - 64;
            n = this.a(context.u, 0, n, arrc, arrlabel);
            context.p = 4;
            context.t = 1;
        } else {
            n3 = this.readUnsignedShort(n);
            n += 2;
            if (n2 == 247) {
                n = this.a(context.u, 0, n, arrc, arrlabel);
                context.p = 4;
                context.t = 1;
            } else if (n2 >= 248 && n2 < 251) {
                context.p = 2;
                context.r = 251 - n2;
                context.q -= context.r;
                context.t = 0;
            } else if (n2 == 251) {
                context.p = 3;
                context.t = 0;
            } else if (n2 < 255) {
                int n4 = bl2 ? context.q : 0;
                for (int i = n2 - 251; i > 0; --i) {
                    n = this.a(context.s, n4++, n, arrc, arrlabel);
                }
                context.p = 1;
                context.r = n2 - 251;
                context.q += context.r;
                context.t = 0;
            } else {
                context.p = 0;
                int n5 = this.readUnsignedShort(n);
                n += 2;
                context.r = n5;
                context.q = n5;
                int n6 = 0;
                while (n5 > 0) {
                    n = this.a(context.s, n6++, n, arrc, arrlabel);
                    --n5;
                }
                n5 = this.readUnsignedShort(n);
                n += 2;
                context.t = n5;
                n6 = 0;
                while (n5 > 0) {
                    n = this.a(context.u, n6++, n, arrc, arrlabel);
                    --n5;
                }
            }
        }
        context.o += n3 + 1;
        this.readLabel(context.o, arrlabel);
        return n;
    }

    private void a(Context context) {
        String string = context.g;
        Object[] arrobject = context.s;
        int n = 0;
        if ((context.e & 8) == 0) {
            arrobject[n++] = "<init>".equals(context.f) ? Opcodes.UNINITIALIZED_THIS : this.readClass(this.header + 2, context.c);
        }
        int n2 = 1;
        block8 : do {
            int n3 = n2;
            switch (string.charAt(n2++)) {
                case 'B': 
                case 'C': 
                case 'I': 
                case 'S': 
                case 'Z': {
                    arrobject[n++] = Opcodes.INTEGER;
                    continue block8;
                }
                case 'F': {
                    arrobject[n++] = Opcodes.FLOAT;
                    continue block8;
                }
                case 'J': {
                    arrobject[n++] = Opcodes.LONG;
                    continue block8;
                }
                case 'D': {
                    arrobject[n++] = Opcodes.DOUBLE;
                    continue block8;
                }
                case '[': {
                    while (string.charAt(n2) == '[') {
                        ++n2;
                    }
                    if (string.charAt(n2) == 'L') {
                        ++n2;
                        while (string.charAt(n2) != ';') {
                            ++n2;
                        }
                    }
                    arrobject[n++] = string.substring(n3, ++n2);
                    continue block8;
                }
                case 'L': {
                    while (string.charAt(n2) != ';') {
                        ++n2;
                    }
                    arrobject[n++] = string.substring(n3 + 1, n2++);
                    continue block8;
                }
            }
            break;
        } while (true);
        context.q = n;
    }
}

