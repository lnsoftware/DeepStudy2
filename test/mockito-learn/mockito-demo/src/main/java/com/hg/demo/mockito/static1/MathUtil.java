package com.hg.demo.mockito.static1;

/**
 * Created by hg on 2015/12/28.
 */

public abstract class MathUtil {

    public static final int addInteger(int a, int b) {
        return a + b;
    }

    private MathUtil() {}
}
