//package com.hg.spring.cache.rediscache.cache;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.script.RedisScript;
//
//import java.util.List;
//
///**
// * Created by hg on 2016/8/4.
// */
//@Slf4j
//public class LogStringRedisTemplate  extends StringRedisTemplate{
//
//
//
//    @Override
//    public <T> T execute(final RedisCallback<T> action, final boolean exposeConnection, final boolean pipeline) {
//        try {
//            return super.execute(action, exposeConnection, pipeline);
//        }
//        catch(final Throwable t) {
//            logger.warn("Error executing cache operation: {}", t.getMessage());
//            return null;
//        }
//    }
//
//
//    @Override
//    public <T> T execute(final RedisScript<T> script, final List<K> keys, final Object... args) {
//        try {
//            return super.execute(script, keys, args);
//        }
//        catch(final Throwable t) {
//            logger.warn("Error executing cache operation: {}", t.getMessage());
//            return null;
//        }
//    }
//
//}
