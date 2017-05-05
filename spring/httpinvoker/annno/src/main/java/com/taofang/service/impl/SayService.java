package com.taofang.service.impl;

import com.taofang.service.IHelloService;
import com.taofang.service.ISayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SayService implements ISayService {

    @Autowired
    private IHelloService service;

    public void ping() {

        System.out.println("Hello");
    }


}
