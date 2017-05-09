package com.hg.dubbo.study.spi;

public interface Spi {

    boolean isSupport(String name);

    public String sayHello();

}