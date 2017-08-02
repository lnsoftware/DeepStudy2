package com.gmail.mosoft521.ch02.xss;

/**
 * 栈深度
 * 函数参数和局部变量都是局部变量
 * -Xss1m
 *
 * @author Administrator
 */
public class TestStackDeep {
    private static int count = 0;
    private static int count2 = 0;
    private static int count3 = 0;
    private static int count4 = 0;

    public static void recursion() {
        count++;
        recursion();
    }

    public static void recursion2() {
        long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
        count2++;
        recursion2();
    }

    public static void recursion3(long a, long b, long c) {
        count3++;
        recursion3(a, b, c);
    }


    public static void recursion4(long a, long b, long c) {
        long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
        count4++;
        recursion4(a, b, c);
    }


    public static void main(String args[]) {
        try {
            recursion();
        } catch (Throwable e) {
            System.out.println("deep of calling = " + count);
        }

        try {
            recursion2();
        } catch (Throwable e) {
            System.out.println("deep of calling = " + count2);
        }

        try {
            recursion3(0L,0L,0L);//1
        } catch (Throwable e) {
            System.out.println("deep of calling = " + count3);
//            e.printStackTrace();
        }

        try {
            recursion4(0L,0L,0L);//1
        } catch (Throwable e) {
            System.out.println("deep of calling = " + count4);
//            e.printStackTrace();
        }
    }
}
/*
1
-Xss128K
deep of calling = 298
java.lang.StackOverflowError

-Xss256K
deep of calling = 1929
java.lang.StackOverflowError

2
-Xss128K
deep of calling = 1822
java.lang.StackOverflowError

-Xss256K
deep of calling = 3432
java.lang.StackOverflowError
第二次运行：
java.lang.StackOverflowError
deep of calling = 3779
第三次运行：
deep of calling = 3186
java.lang.StackOverflowError
 */