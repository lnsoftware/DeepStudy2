package com.hg.spring.cache.rediscache;

import com.alibaba.fastjson.JSON;
import com.hg.spring.cache.rediscache.config.RedisCacheName;
import com.hg.spring.cache.rediscache.entity.User;
import com.hg.spring.cache.rediscache.service.IUserInfoService;
import com.hg.spring.cache.rediscache.service.impl.CacheNameFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
//@ContextConfiguration(classes = {RedisCacheConfig.class})
public class RedisCachableTest {


    @Autowired
    private StringRedisTemplate redisTemplate;

    public void test(){


//        redisTemplate.opsForValue().
    }
}
