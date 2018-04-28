package com.zhiyin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by wangqinghui on 2018/3/28.
 */
@Component
public class Task {

    @Autowired
    private UserService userService;

    @Scheduled(fixedRate = 5000)
    public void task(){
        userService.getUserId(null);
    }
}
