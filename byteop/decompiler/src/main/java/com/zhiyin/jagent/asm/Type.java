/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.Type
 */
package com.zhiyin.jagent.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/*
 * Exception performing whole class analysis ignored.
 */
public class Type {
    public static final int VOID = 0;
    public static final int BOOLEAN = 1;
    public static final int CHAR = 2;
    public static final int BYTE = 3;
    public static final int SHORT = 4;
    public static final int INT = 5;
    public static final int FLOAT = 6;
    public static final int LONG = 7;
    public static final int DOUBLE = 8;
    public static final int ARRAY = 9;
    public static final int OBJECT = 10;
    public static final int METHOD = 11;
    public static final Type VOID_TYPE;
    public static final Type BOOLEAN_TYPE;
    public static final Type CHAR_TYPE;
    public static final Type BYTE_TYPE;
    public static final Type SHORT_TYPE;
    public static final Type INT_TYPE;
    public static final Type FLOAT_TYPE;
    public static final Type LONG_TYPE;
    public static final Type DOUBLE_TYPE;
    private final int a;
    private final char[] b;
    private final int c;
    private final int d;

    private Type(int n, char[] arrc, int n2, int n3) {
        this.a = n;
        this.b = arrc;
        this.c = n2;
        this.d = n3;
    }

    static {
        Type._clinit_();
        VOID_TYPE = new Type(0, null, 1443168256, 1);
        BOOLEAN_TYPE = new Type(1, null, 1509950721, 1);
        CHAR_TYPE = new Type(2, null, 1124075009, 1);
        BYTE_TYPE = new Type(3, null, 1107297537, 1);
        SHORT_TYPE = new Type(4, null, 1392510721, 1);
        INT_TYPE = new Type(5, null, 1224736769, 1);
        FLOAT_TYPE = new Type(6, null, 1174536705, 1);
        LONG_TYPE = new Type(7, null, 1241579778, 1);
        DOUBLE_TYPE = new Type(8, null, 1141048066, 1);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Type)) {
            return false;
        }
        Type type = (Type)object;
        if (this.a != type.a) {
            return false;
        }
        if (this.a >= 9) {
            if (this.d != type.d) {
                return false;
            }
            int n = this.c;
            int n2 = type.c;
            int n3 = n + this.d;
            while (n < n3) {
                if (this.b[n] != type.b[n2]) {
                    return false;
                }
                ++n;
                ++n2;
            }
        }
        return true;
    }

    public String toString() {
        return this.getDescriptor();
    }

    public int hashCode() {
        int n = 13 * this.a;
        if (this.a >= 9) {
            int n2;
            int n3 = n2 + this.d;
            for (n2 = this.c; n2 < n3; ++n2) {
                n = 17 * (n + this.b[n2]);
            }
        }
        return n;
    }

    public String getDescriptor() {
        StringBuffer stringBuffer = new StringBuffer();
        this.a(stringBuffer);
        return stringBuffer.toString();
    }

    public static String getDescriptor(Class class_) {
        StringBuffer stringBuffer = new StringBuffer();
        Type.a((StringBuffer)stringBuffer, (Class)class_);
        return stringBuffer.toString();
    }

    public static Type getReturnType(Method method) {
        return Type.getType(method.getReturnType());
    }

    public Type getReturnType() {
        return Type.getReturnType((String)this.getDescriptor());
    }

    public static Type getReturnType(String string) {
        char[] arrc = string.toCharArray();
        return Type.a((char[])arrc, (int)(string.indexOf(41) + 1));
    }

    public static Type getType(String string) {
        return Type.a((char[])string.toCharArray(), (int)0);
    }

    public static Type getType(Class class_) {
        if (class_.isPrimitive()) {
            if (class_ == Integer.TYPE) {
                return INT_TYPE;
            }
            if (class_ == Void.TYPE) {
                return VOID_TYPE;
            }
            if (class_ == Boolean.TYPE) {
                return BOOLEAN_TYPE;
            }
            if (class_ == Byte.TYPE) {
                return BYTE_TYPE;
            }
            if (class_ == Character.TYPE) {
                return CHAR_TYPE;
            }
            if (class_ == Short.TYPE) {
                return SHORT_TYPE;
            }
            if (class_ == Double.TYPE) {
                return DOUBLE_TYPE;
            }
            if (class_ == Float.TYPE) {
                return FLOAT_TYPE;
            }
            return LONG_TYPE;
        }
        return Type.getType((String)Type.getDescriptor((Class)class_));
    }

    public static Type getType(Constructor constructor) {
        return Type.getType((String)Type.getConstructorDescriptor((Constructor)constructor));
    }

    public static Type getType(Method method) {
        return Type.getType((String)Type.getMethodDescriptor((Method)method));
    }

    public int getSize() {
        return this.b == null ? this.c & 255 : 1;
    }

    public static Type getMethodType(String string) {
        return Type.a((char[])string.toCharArray(), (int)0);
    }

    public static /* varargs */ Type getMethodType(Type type, Type ... arrtype) {
        return Type.getType((String)Type.getMethodDescriptor((Type)type, (Type[])arrtype));
    }

    public String getClassName() {
        switch (this.a) {
            case 0: {
                return "void";
            }
            case 1: {
                return "boolean";
            }
            case 2: {
                return "char";
            }
            case 3: {
                return "byte";
            }
            case 4: {
                return "short";
            }
            case 5: {
                return "int";
            }
            case 6: {
                return "float";
            }
            case 7: {
                return "long";
            }
            case 8: {
                return "double";
            }
            case 9: {
                StringBuffer stringBuffer = new StringBuffer(this.getElementType().getClassName());
                for (int i = this.getDimensions(); i > 0; --i) {
                    stringBuffer.append("[]");
                }
                return stringBuffer.toString();
            }
            case 10: {
                return new String(this.b, this.c, this.d).replace('/', '.');
            }
        }
        return null;
    }

    public int getOpcode(int n) {
        if (n == 46 || n == 79) {
            return n + (this.b == null ? (this.c & 65280) >> 8 : 4);
        }
        return n + (this.b == null ? (this.c & 16711680) >> 16 : 4);
    }

    public int getSort() {
        return this.a;
    }

    public static String getInternalName(Class class_) {
        return class_.getName().replace('.', '/');
    }

    public String getInternalName() {
        return new String(this.b, this.c, this.d);
    }

    static /* synthetic */ void _clinit_() {
    }

    public Type[] getArgumentTypes() {
        return Type.getArgumentTypes((String)this.getDescriptor());
    }

    public static Type[] getArgumentTypes(String string) {
        char c;
        char[] arrc = string.toCharArray();
        int n = 1;
        int n2 = 0;
        while ((c = arrc[n++]) != ')') {
            if (c == 'L') {
                while (arrc[n++] != ';') {
                }
                ++n2;
                continue;
            }
            if (c == '[') continue;
            ++n2;
        }
        Type[] arrtype = new Type[n2];
        n = 1;
        n2 = 0;
        while (arrc[n] != ')') {
            arrtype[n2] = Type.a((char[])arrc, (int)n);
            n += arrtype[n2].d + (arrtype[n2].a == 10 ? 2 : 0);
            ++n2;
        }
        return arrtype;
    }

    public static Type[] getArgumentTypes(Method method) {
        Class<?>[] arrclass = method.getParameterTypes();
        Type[] arrtype = new Type[arrclass.length];
        for (int i = arrclass.length - 1; i >= 0; --i) {
            arrtype[i] = Type.getType(arrclass[i]);
        }
        return arrtype;
    }

    public static Type getObjectType(String string) {
        char[] arrc = string.toCharArray();
        return new Type(arrc[0] == '[' ? 9 : 10, arrc, 0, arrc.length);
    }

    public static int getArgumentsAndReturnSizes(String string) {
        int n = 1;
        int n2 = 1;
        do {
            char c;
            if ((c = string.charAt(n2++)) == ')') {
                c = string.charAt(n2);
                return n << 2 | (c == 'V' ? 0 : (c == 'D' || c == 'J' ? 2 : 1));
            }
            if (c == 'L') {
                while (string.charAt(n2++) != ';') {
                }
                ++n;
                continue;
            }
            if (c == '[') {
                while ((c = string.charAt(n2)) == '[') {
                    ++n2;
                }
                if (c != 'D' && c != 'J') continue;
                --n;
                continue;
            }
            if (c == 'D' || c == 'J') {
                n += 2;
                continue;
            }
            ++n;
        } while (true);
    }

    public int getArgumentsAndReturnSizes() {
        return Type.getArgumentsAndReturnSizes((String)this.getDescriptor());
    }

    public static /* varargs */ String getMethodDescriptor(Type type, Type ... arrtype) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        for (int i = 0; i < arrtype.length; ++i) {
            arrtype[i].a(stringBuffer);
        }
        stringBuffer.append(')');
        type.a(stringBuffer);
        return stringBuffer.toString();
    }

    public static String getMethodDescriptor(Method method) {
        Class<?>[] arrclass = method.getParameterTypes();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        for (int i = 0; i < arrclass.length; ++i) {
            Type.a((StringBuffer)stringBuffer, arrclass[i]);
        }
        stringBuffer.append(')');
        Type.a((StringBuffer)stringBuffer, method.getReturnType());
        return stringBuffer.toString();
    }

    public static String getConstructorDescriptor(Constructor constructor) {
        Class<?>[] arrclass = constructor.getParameterTypes();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        for (int i = 0; i < arrclass.length; ++i) {
            Type.a((StringBuffer)stringBuffer, arrclass[i]);
        }
        return stringBuffer.append(")V").toString();
    }

    public int getDimensions() {
        int n = 1;
        while (this.b[this.c + n] == '[') {
            ++n;
        }
        return n;
    }

    public Type getElementType() {
        return Type.a((char[])this.b, (int)(this.c + this.getDimensions()));
    }

    private void a(StringBuffer stringBuffer) {
        if (this.b == null) {
            stringBuffer.append((char)((this.c & -16777216) >>> 24));
        } else if (this.a == 10) {
            stringBuffer.append('L');
            stringBuffer.append(this.b, this.c, this.d);
            stringBuffer.append(';');
        } else {
            stringBuffer.append(this.b, this.c, this.d);
        }
    }

    private static Type a(char[] arrc, int n) {
        switch (arrc[n]) {
            case 'V': {
                return VOID_TYPE;
            }
            case 'Z': {
                return BOOLEAN_TYPE;
            }
            case 'C': {
                return CHAR_TYPE;
            }
            case 'B': {
                return BYTE_TYPE;
            }
            case 'S': {
                return SHORT_TYPE;
            }
            case 'I': {
                return INT_TYPE;
            }
            case 'F': {
                return FLOAT_TYPE;
            }
            case 'J': {
                return LONG_TYPE;
            }
            case 'D': {
                return DOUBLE_TYPE;
            }
            case '[': {
                int n2 = 1;
                while (arrc[n + n2] == '[') {
                    ++n2;
                }
                if (arrc[n + n2] == 'L') {
                    ++n2;
                    while (arrc[n + n2] != ';') {
                        ++n2;
                    }
                }
                return new Type(9, arrc, n, n2 + 1);
            }
            case 'L': {
                int n3 = 1;
                while (arrc[n + n3] != ';') {
                    ++n3;
                }
                return new Type(10, arrc, n + 1, n3 - 1);
            }
        }
        return new Type(11, arrc, n, arrc.length - n);
    }

    private static void a(StringBuffer stringBuffer, Class class_) {
        Class class_2 = class_;
        do {
            if (class_2.isPrimitive()) {
                int n = class_2 == Integer.TYPE ? 73 : (class_2 == Void.TYPE ? 86 : (class_2 == Boolean.TYPE ? 90 : (class_2 == Byte.TYPE ? 66 : (class_2 == Character.TYPE ? 67 : (class_2 == Short.TYPE ? 83 : (class_2 == Double.TYPE ? 68 : (class_2 == Float.TYPE ? 70 : 74)))))));
                stringBuffer.append((char)n);
                return;
            }
            if (!class_2.isArray()) break;
            stringBuffer.append('[');
            class_2 = class_2.getComponentType();
        } while (true);
        stringBuffer.append('L');
        String string = class_2.getName();
        int n = string.length();
        for (int i = 0; i < n; ++i) {
            char c = string.charAt(i);
            stringBuffer.append(c == '.' ? '/' : (char)c);
        }
        stringBuffer.append(';');
    }
}

