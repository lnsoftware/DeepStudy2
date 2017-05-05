package com.hg.sb.act.task;

import com.hg.sb.act.service.IUserInfoService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by hg on 2016/8/2.
 */
@Component
public class DemoTask {

    @Autowired
    private IUserInfoService userInfoService;

    @Scheduled(fixedRate = 1000 * 2, initialDelay = 1000 * 2)
    public void getUser() {
        userInfoService.getName(RandomUtils.nextLong(10, 200));
    }

}
