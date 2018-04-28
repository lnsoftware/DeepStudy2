import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.embedded.RedisServer;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangqinghui on 2018/3/6.
 */
public class RedisSercerTest {

    private RedisServer redisServer;

    @Test
    public void testSimpleOperationsAfterRun() throws Exception {
        redisServer = new RedisServer(6379);
        redisServer.start();

        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = new JedisPool("localhost", 6379);
            jedis = pool.getResource();
            jedis.mset("abc", "1", "def", "2");

            assertEquals("1", jedis.mget("abc").get(0));
            assertEquals("2", jedis.mget("def").get(0));
            assertEquals(null, jedis.mget("xyz").get(0));
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
            redisServer.stop();
        }
    }

}
