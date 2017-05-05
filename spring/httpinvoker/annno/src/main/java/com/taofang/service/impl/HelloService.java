package com.taofang.service.impl;

import com.taofang.scanner.annotation.HessianService;
import com.taofang.service.IHelloService;
import org.springframework.stereotype.Component;

@Component
@HessianService("helloServiceExporter")
public class HelloService implements IHelloService {

    public String say(String name) {
        return "Hello," + name;

    }

}
