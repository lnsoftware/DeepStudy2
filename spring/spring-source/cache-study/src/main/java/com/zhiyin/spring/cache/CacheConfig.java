package com.zhiyin.spring.cache;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hg on 2017/6/30.
 */
@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public SimpleCacheManager SimpleCacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("default")));
        return cacheManager;
    }
}
