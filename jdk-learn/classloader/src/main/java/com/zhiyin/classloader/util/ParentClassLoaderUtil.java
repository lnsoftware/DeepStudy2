package com.zhiyin.classloader.util;

/**
 * Created by hg on 2017/6/24.
 */
public class ParentClassLoaderUtil {

    public static void main(String[] args) {
        ClassLoader loader = LoaderPath.class.getClassLoader();    //获得加载ClassLoaderTest.class这个类的类加载器
        while(loader != null) {
            System.out.println(loader);
            loader = loader.getParent();    //获得父类加载器的引用
        }
        System.out.println(loader);
    }

}
