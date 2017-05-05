package com.zhiyin.rpc.shi.demo.service.impl;

import com.zhiyin.rpc.httpinvoker.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String getName() {
        log.info("call");
        return "bob dylan";
    }


    @Override
    public String sleep(int sleep) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Ok";
    }
}
