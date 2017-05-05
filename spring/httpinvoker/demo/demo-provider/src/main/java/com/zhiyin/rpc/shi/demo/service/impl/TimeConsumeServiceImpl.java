package com.zhiyin.rpc.shi.demo.service.impl;

import com.zhiyin.rpc.httpinvoker.demo.service.TimeConsumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class TimeConsumeServiceImpl implements TimeConsumeService {

    @Override
    public String timeConsume() {
        try {
            Thread.currentThread().sleep(2000L);
        } catch (InterruptedException e) {
            log.error("timeCon error,",e);
        }
        log.info("call time con");
        return "time con "+ UUID.randomUUID().toString();
    }



}
