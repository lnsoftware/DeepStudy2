package com.hg.spring.cache.rediscache.service.impl.ck;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.lang.annotation.*;


@Caching(
        cacheable = {
                @Cacheable() // 默认key
        },
        put = {
//                @CachePut(  key = "'users.userinfo.id.'+ #result.id", condition = "#result != null"),
//                @CachePut(  key = "'users.userinfo.name.'+ #result.name", condition = "#result != null")
        }
)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface UserSaveCache {

}