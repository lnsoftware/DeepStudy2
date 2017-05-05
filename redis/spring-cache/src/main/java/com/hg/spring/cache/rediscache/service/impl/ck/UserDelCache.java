package com.hg.spring.cache.rediscache.service.impl.ck;

import com.hg.spring.cache.rediscache.service.impl.CacheNameFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;

import java.lang.annotation.*;

@Caching(
        evict = {
                @CacheEvict(key = "#root.targetClass.name+'.findById'+#id"),
                @CacheEvict(value = { CacheNameFactory.UserSelAll }, allEntries = true) // 删除查询全部
        }
)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface UserDelCache {

}