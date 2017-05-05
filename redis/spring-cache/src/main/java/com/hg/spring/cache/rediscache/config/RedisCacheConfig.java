package com.hg.spring.cache.rediscache.config;

import com.google.common.collect.Maps;
import com.hg.spring.cache.rediscache.cache.LoggingRedisTemplate;
import com.hg.spring.cache.rediscache.cache.RedisCacheErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.lang.reflect.Method;
import java.util.Map;

@Configuration
@PropertySource("classpath:/redis.properties")
@EnableCaching(proxyTargetClass = true)
//public class RedisCacheConfig implements CachingConfigurer {
public class RedisCacheConfig extends CachingConfigurerSupport {

	private @Value("${redis.host}") String redisHostName;
	private @Value("${redis.port}") int redisPort;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(redisHostName);
		factory.setPort(redisPort);
		factory.setUsePool(true);
//		factory.setTimeout(1000);
		
		return factory;
	}

// String 类型的模板
//	@Bean
//	StringRedisTemplate2 redisTemplate() {
//		StringRedisTemplate2 redisTemplate = new StringRedisTemplate2();
//		redisTemplate.setConnectionFactory(jedisConnectionFactory());
//		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//		return redisTemplate;
//	}
	
	// String类型的模板
	@Bean
	RedisTemplate redisTemplate() {
//		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		LoggingRedisTemplate<String,String> redisTemplate = new LoggingRedisTemplate<String,String>();

		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		
//		JDK序列化，不方便查看
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		
		// json序列化，方便查看
//		redisTemplate.setValueSerializer(new JacksonJsonRedisSerializer (Object.class));

		return redisTemplate;
	}

	// @Bean
	// RedisTemplate<String, Object> redisTemplate() {
	//
	// RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String,
	// Object>();
	// redisTemplate.setConnectionFactory(jedisConnectionFactory());
	//
	// return redisTemplate;
	// }

	@Bean
	public CacheManager cacheManager() {

		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());
		cacheManager.setDefaultExpiration( 60 * 2L ); // Sets the default expire time
													// (in seconds)


        // 可以对每个cache的超时时间进行设置
        Map<String,Long> expire = Maps.newHashMap();

        expire.put("userCache",2*60L);
//        expire.put("users.userinfo")
        cacheManager.setExpires(expire);

		return cacheManager;
	}

	@Bean
	public KeyGenerator keyGenerator() {


		
		// 使用参数对象的toString方法作为key，对象必须重写toString方法，否则问题很严重
		//如果对象没有重写toString方法，每次生成的对象都不一样，cachekey也不一样，起不到缓存的作用。
		KeyGenerator tmp =  new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder();
				sb.append(o.getClass().getName());
				sb.append(".");
				sb.append(method.getName());
//                if(objects != null && objects.length > 1){
//                    sb.append(".");
//                }
				for (Object obj : objects) {
					sb.append(obj);
//					sb.append(JSON.toJSONString(obj));
				}
				return sb.toString();
			}
		};

		// 使用参数对象的toString方法作为key，对象必须重写toString方法，否则问题很严重
		//如果对象没有重写toString方法，每次生成的对象都不一样，cachekey也不一样，起不到缓存的作用。
//		tmp =  new KeyGenerator() {
//			@Override
//			public Object generate(Object o, Method method, Object... objects) {
//				StringBuilder sb = new StringBuilder();
//				sb.append(o.getClass().getName());
//				sb.append(method.getName());
//				for (Object obj : objects) {
//					sb.append(obj.toString());
//				}
//				return sb.toString();
//			}
//		};
		
		
		// SimpleKeyGenerator 将 参数强制转为String作为cachekey，如果参数是对象会报java.lang.ClassCastException
		//tmp = new SimpleKeyGenerator();


        // 用于controller的请求缓存
//		tmp = new ControllerKeyGenerator();


		return tmp;
		
	}
	
	

	// redis 错误处理方式
	public CacheErrorHandler errorHandler() {
		return new RedisCacheErrorHandler();
	}


}