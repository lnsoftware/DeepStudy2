package sss;

import java.util.Arrays;

interface IA {

    int plus(int a, int b);

}

public class A implements IA {

    public A() {

    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        // invokestatic
        A.add(10, 20);

        // invokespecial
        A a = new A();
        a.power(10);
        // invokevirtual
        a.minus(20, 10);
        // invokeinterface
        IA a1 = (IA) a;
        a1.plus(10, 20);
        // invokedynamic
        Arrays.asList(1, 2, 3, 4).stream().forEach(n -> {
            System.out.println(n);
        });
    }

    public int minus(int a, int b) {
        return a - b;
    }

    public int plus(int a, int b) {
        return a * b;
    }

    private int power(int a) {
        int hash = super.hashCode();
        return a * a;
    }

}