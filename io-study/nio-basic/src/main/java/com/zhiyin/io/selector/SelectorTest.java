package com.zhiyin.io.selector;

/**
 * Created by wangqinghui on 2017/11/1.
 */
public class SelectorTest {

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(java.nio.channels.spi.SelectorProvider.provider());
    }
}
