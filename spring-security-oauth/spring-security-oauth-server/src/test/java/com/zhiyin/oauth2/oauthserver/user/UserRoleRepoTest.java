package com.zhiyin.oauth2.oauthserver.user;

import com.alibaba.fastjson.JSON;
import com.zhiyin.oauth2.oauthserver.OauthServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(OauthServerApplication.class)
public class UserRoleRepoTest {

    @Autowired
    private UserRoleRepo userRoleRepo;



    @Test
    public void test() throws Exception {
        List<UserRole> roles = userRoleRepo.findByUsername("admin");

        log.info(JSON.toJSONString(roles));
    }


}