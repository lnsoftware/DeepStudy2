package com.zhiyin.cases.shutdown.component;

import com.zhiyin.cases.shutdown.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class TaskService {

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private UserMapper userMapper;

    @Scheduled(fixedRate = 1)
    public void fixedRate() {
        // 保证线程池一直有任务
        for (int i = 0; i < 1000; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        userMapper.findByState("1");
                    } catch (Exception e) {
                        log.warn(e.getMessage());
                    }
                }
            });
        }
    }

    @GetMapping(value = "/")
    @ResponseBody
    public String getUsers() {
        userMapper.findByState("1");
        return "";
    }

    @PreDestroy
    public void close() {
        log.info("task service destory");
    }
}
