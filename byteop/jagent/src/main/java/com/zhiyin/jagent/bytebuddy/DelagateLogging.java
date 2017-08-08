package com.zhiyin.jagent.bytebuddy;

import net.bytebuddy.implementation.bind.annotation.Origin;

import java.lang.reflect.Method;

/**
 * Created by fupan on 16-4-9.
 */
public class DelagateLogging {

    public static void logging(@Origin Method method) {
        System.out.println("logging");
    }
}