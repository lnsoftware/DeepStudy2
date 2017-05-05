//package com.zhiyin.search.es.redis;
//
//import com.zhiyin.search.es.controller.ControllerTestBase;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.junit.Test;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import javax.annotation.Resource;
//
//public class RedisTemplateTest extends ControllerTestBase {
//
//	private Log log = LogFactory.getLog(RedisTemplateTest.class);
//
//
//	@Resource(name="redisTemplate")
//	protected RedisTemplate<String,String> redisTemplate;
//
//
////	@ClassRule
////	public static EmbeddedRedis embeddedRedisRule = EmbeddedRedisRuleBuilder.newEmbeddedRedisRule().build();
////
////	@Before
////	public void setup() {
////		Jedis embeddedRedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
////		JedisPool jedisPool1 = Mockito.mock(JedisPool.class);
////		Mockito.when(jedisPool1.getResource()).thenReturn(embeddedRedis);
////
////		JedisPool jedisPool2 = Mockito.mock(JedisPool.class);
////		Mockito.when(jedisPool2.getResource()).thenReturn(embeddedRedis);
////
////		jedisTemplate = new JedisShardedTemplate(new JedisPool[] { jedisPool1, jedisPool2 });
////	}
//
//
//	@Test
//	public void test() {
//		redisTemplate.opsForValue().set("bb", "ssss");
//	}
//
//}
