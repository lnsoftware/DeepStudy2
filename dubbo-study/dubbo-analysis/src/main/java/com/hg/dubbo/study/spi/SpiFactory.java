package com.hg.dubbo.study.spi;

import lombok.extern.slf4j.Slf4j;

import java.util.ServiceLoader;

@Slf4j
public class SpiFactory {

    //读取配置获取所有实现

    private static ServiceLoader<Spi> spiLoader = ServiceLoader.load(Spi.class);

    //根据名字选取对应实现

    public static Spi getSpi(String name) {

        for (Spi spi : spiLoader) {

            if (spi.isSupport(name)) {

                return spi;

            }

        }

        return null;

    }

    public static void main(String[] args) {
        log.info(getSpi("SPIA").sayHello());
    }
}