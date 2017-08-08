package com.zhiyin.thrift.example.service;

import org.apache.thrift.TException;

public class HelloWorldImpl implements HelloWorldService.Iface {
    public HelloWorldImpl() {
    }

    @Override
    public String hello(String username) throws TException {
        return "Hi," + username + " welcome to jianshu";
    }
}