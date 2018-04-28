/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.zhiyin.jagent.asm.Item
 */
package com.zhiyin.jagent.asm;

final class Item {
    int a;
    int b;
    int c;
    long d;
    String g;
    String h;
    String i;
    int j;
    Item k;

    Item(int n, Item item) {
        this.a = n;
        this.b = item.b;
        this.c = item.c;
        this.d = item.d;
        this.g = item.g;
        this.h = item.h;
        this.i = item.i;
        this.j = item.j;
    }

    Item(int n) {
        this.a = n;
    }

    Item() {
    }

    void a(int n, String string, String string2, String string3) {
        this.b = n;
        this.g = string;
        this.h = string2;
        this.i = string3;
        switch (n) {
            case 7: {
                this.c = 0;
            }
            case 1: 
            case 8: 
            case 16: 
            case 30: {
                this.j = Integer.MAX_VALUE & n + string.hashCode();
                return;
            }
            case 12: {
                this.j = Integer.MAX_VALUE & n + string.hashCode() * string2.hashCode();
                return;
            }
        }
        this.j = Integer.MAX_VALUE & n + string.hashCode() * string2.hashCode() * string3.hashCode();
    }

    void a(String string, String string2, int n) {
        this.b = 18;
        this.d = n;
        this.g = string;
        this.h = string2;
        this.j = Integer.MAX_VALUE & 18 + n * this.g.hashCode() * this.h.hashCode();
    }

    void a(int n, int n2) {
        this.b = 33;
        this.c = n;
        this.j = n2;
    }

    boolean a(Item item) {
        switch (this.b) {
            case 1: 
            case 7: 
            case 8: 
            case 16: 
            case 30: {
                return item.g.equals(this.g);
            }
            case 5: 
            case 6: 
            case 32: {
                return item.d == this.d;
            }
            case 3: 
            case 4: {
                return item.c == this.c;
            }
            case 31: {
                return item.c == this.c && item.g.equals(this.g);
            }
            case 12: {
                return item.g.equals(this.g) && item.h.equals(this.h);
            }
            case 18: {
                return item.d == this.d && item.g.equals(this.g) && item.h.equals(this.h);
            }
        }
        return item.g.equals(this.g) && item.h.equals(this.h) && item.i.equals(this.i);
    }

    void a(int n) {
        this.b = 3;
        this.c = n;
        this.j = Integer.MAX_VALUE & this.b + n;
    }

    void a(long l) {
        this.b = 5;
        this.d = l;
        this.j = Integer.MAX_VALUE & this.b + (int)l;
    }

    void a(float f) {
        this.b = 4;
        this.c = Float.floatToRawIntBits(f);
        this.j = Integer.MAX_VALUE & this.b + (int)f;
    }

    void a(double d) {
        this.b = 6;
        this.d = Double.doubleToRawLongBits(d);
        this.j = Integer.MAX_VALUE & this.b + (int)d;
    }
}

