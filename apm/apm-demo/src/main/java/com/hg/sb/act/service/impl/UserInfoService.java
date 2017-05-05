package com.hg.sb.act.service.impl;

import com.codahale.metrics.annotation.Timed;
import com.hg.sb.act.service.IUserInfoService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

/**
 * Created by hg on 2016/8/2.
 */
@Service
public class UserInfoService implements IUserInfoService {

    @Timed(name = "service.getName")
    @Override
    public String getName(Long id) {
        try {
            Thread.sleep(RandomUtils.nextLong(10, 200));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "user_" + id;
    }
}
