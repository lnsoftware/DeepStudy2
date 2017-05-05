package com.zhiyin.cloudeye.demo.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HelloService {

//    @HystrixCommand
    public String random() {
        return UUID.randomUUID().toString();
    }

}
