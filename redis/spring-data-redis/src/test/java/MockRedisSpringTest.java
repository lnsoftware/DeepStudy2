import config.RedisTemplateConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.embedded.RedisServer;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangqinghui on 2018/3/6.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisTemplateConfig.class})
public class MockRedisSpringTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private RedisServer redisServer;

    @Test
    public void testSimpleOperationsAfterRun() throws Exception {
        redisServer = new RedisServer(6379);
        redisServer.start();

        try {
            stringRedisTemplate.opsForSet().add("abc", "1", "def", "2");

            assertEquals("1", stringRedisTemplate.opsForSet().pop("abc"));
            assertEquals("1", stringRedisTemplate.opsForSet().pop("abc"));
//            assertEquals(null, jedis.mget("xyz").get(0));
        } finally {
            redisServer.stop();
        }
    }

}
