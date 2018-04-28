package com.mindflow.dubbox.provider.service;

import com.mindflow.api.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author Ricky Fung
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHi(String msg) {
        return String.format("Hi %s", msg);
    }
}
