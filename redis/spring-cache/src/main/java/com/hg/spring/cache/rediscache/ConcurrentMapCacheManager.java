package com.hg.spring.cache.rediscache;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * 功能说明：自定义的ConcurrentMapCacheManager，新增超时时间和最大存储限制
 * 作者：liuxing(2015-04-13 18:44)
 */
public class ConcurrentMapCacheManager implements CacheManager {

    /**
     * 过期时间，秒（自定义）
     */
    private long exp = 1800;
    /**
     * 最大存储数量 （自定义）
     */
    private long maximumSize = 1000;

    public void setExp(long exp) {
        this.exp = exp;
    }

    public void setMaximumSize(long maximumSize) {
        this.maximumSize = maximumSize;
    }

    /**
     * 创建一个缓存容器，这个方法改写为使用Guava的Cache
     * @param name
     * @return
     */
    protected Cache createConcurrentMapCache(String name) {
        return new ConcurrentMapCache(name, CacheBuilder.newBuilder().expireAfterWrite(this.exp, TimeUnit.SECONDS)
                                                                     .maximumSize(this.maximumSize)
                                                                     .build()
                                                                     .asMap(), false);
    }

    @Override
    public Cache getCache(String name) {
        return null;
    }

    @Override
    public Collection<String> getCacheNames() {
        return null;
    }
}