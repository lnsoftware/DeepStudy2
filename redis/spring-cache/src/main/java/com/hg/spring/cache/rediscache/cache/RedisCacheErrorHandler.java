package com.hg.spring.cache.rediscache.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

public class RedisCacheErrorHandler implements CacheErrorHandler {

	 private static final Logger logger = LoggerFactory.getLogger(RedisCacheErrorHandler.class);

	
	@Override
	public void handleCacheGetError(RuntimeException exception, Cache cache,
			Object key) {
		logger.error("handleCacheGetError",exception);
	}

	@Override
	public void handleCachePutError(RuntimeException exception, Cache cache,
			Object key, Object value) {
		logger.error("handleCachePutError",exception);
	}

	@Override
	public void handleCacheEvictError(RuntimeException exception, Cache cache,
			Object key) {
		logger.error("handleCacheEvictError",exception);
	}

	@Override
	public void handleCacheClearError(RuntimeException exception, Cache cache) {
		logger.error("handleCacheClearError",exception);
	}

}
