package com.hg.spring.cache.rediscache.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;


/**
 * An extension of RedisTemplate that logs exceptions instead of letting them propagate.
 * If the Redis server is unavailable, cache operations are always a "miss" and data is fetched from the database.
 */
public class LoggingRedisTemplate<K, V> extends RedisTemplate<K, V> {

    private static final Logger logger = LoggerFactory.getLogger(LoggingRedisTemplate.class);


    @Override
    public <T> T execute(final RedisCallback<T> action, final boolean exposeConnection, final boolean pipeline) {
        try {
            logger.info("call ex");
            return super.execute(action, exposeConnection, pipeline);
        }
        catch(final Throwable t) {
            logger.warn("Error executing cache operation: {}", t.getMessage());
            return null;
        }
    }


    @Override
    public <T> T execute(final RedisScript<T> script, final List<K> keys, final Object... args) {
        try {
            return super.execute(script, keys, args);
        }
        catch(final Throwable t) {
            logger.warn("Error executing cache operation: {}", t.getMessage());
            return null;
        }
    }


    @Override
    public <T> T execute(final RedisScript<T> script, final RedisSerializer<?> argsSerializer, final RedisSerializer<T> resultSerializer, final List<K> keys, final Object... args) {
        try {
            return super.execute(script, argsSerializer, resultSerializer, keys, args);
        }
        catch(final Throwable t) {
            logger.warn("Error executing cache operation: {}", t.getMessage());
            return null;
        }
    }

    public <T> T execute(RedisCallback<T> action) {
        logger.info("call");
        return execute(action, isExposeConnection());
    }

    @Override
    public <T> T execute(final SessionCallback<T> session) {
        try {
            return super.execute(session);
        }
        catch(final Throwable t) {
            logger.warn("Error executing cache operation: {}", t.getMessage());
            return null;
        }
    }
}