package com.zhiyin.spring.aop.demo4.service.impl;

import com.zhiyin.spring.aop.demo4.service.IHello;

/**
 * Created by hg on 2017/6/15.
 */
public class HelloImpl implements IHello {
    @Override public String hello(String hello) {
        return "hello "+ hello;
    }
}
