package com.hg.jdk.cl;

public class Hot {
    public void hot() {
        System.out.println(" version 1 : " + this.getClass().getClassLoader());
    }
}