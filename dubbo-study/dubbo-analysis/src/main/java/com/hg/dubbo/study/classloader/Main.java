package com.hg.dubbo.study.classloader;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * Created by hg on 2017/5/9.
 */
public class Main {

    public static void main(String[] args) {

        ClassLoader system = ClassLoader.getSystemClassLoader();
        System.out.println(system);
        ClassLoader context = Thread.currentThread().getContextClassLoader();
        System.out.println(context);
        ClassLoader a = ExtensionLoader.class.getClassLoader();
        System.out.println(a);

    }
}
