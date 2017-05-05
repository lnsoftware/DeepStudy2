package com.hg.dubbo.study.spi;

public class SpiBImpl implements Spi {

    public boolean isSupport(String name) {

        return "SPIB".equalsIgnoreCase(name.trim());

    }

    public String sayHello() {

        return "hello 我是厂商B";

    }

}

