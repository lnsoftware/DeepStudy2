package com.zhiyin.oauth2.oauthserver.user;

import com.alibaba.fastjson.JSON;
import com.zhiyin.oauth2.oauthserver.OauthServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OauthServerApplication.class)
public class UserRoleRepoTest {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Test
    public void test() throws Exception {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(RandomUtils.nextLong(1L,100L));
        userInfo.setTelephone("admin");
        userInfo.setPassword(DigestUtils.md5Hex(userInfo.getTelephone()));
        userInfoRepo.save(userInfo);

        UserInfo sel = userInfoRepo.findByTelephone(userInfo.getTelephone());

        assertThat(sel).isNotNull();
        assertThat(sel.getTelephone()).isEqualTo(userInfo.getTelephone());

    }


}