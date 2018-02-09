package com.zhiyin.classloader.hotdeploy;

public class HelloRequestProcessor {
//    private static final int[] arry = new int[1*1000*1000];
    public void process() {
        System.out.println(" version 1 : " + this.getClass().getClassLoader());
    }
}