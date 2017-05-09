package com.hg.dubbo.study.spi;

public class SpiAImpl implements Spi {

    public boolean isSupport(String name) {

        return "SPIA".equalsIgnoreCase(name.trim());
    }

    public String sayHello() {

        return "hello 我是厂商A";

    }

}