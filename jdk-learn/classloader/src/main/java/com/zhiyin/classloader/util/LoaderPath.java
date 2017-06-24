package com.zhiyin.classloader.util;

import java.net.URL;

/**
 * Created by hg on 2017/6/24.
 */
public class LoaderPath {
    public static void main(String[] args) {

//        BootStrap ClassLoader jar
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }

        System.out.println(System.getProperty("sun.boot.class.path"));

    }
}
