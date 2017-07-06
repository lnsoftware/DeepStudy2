import config.RedisTemplateConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisTemplateConfig.class})
public class RedisCachableTest {

    @Qualifier("stringRedisTemplate")
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void stringRedisTemplate(){
        stringRedisTemplate.opsForValue().set("name","HG");
        String cacheVal = stringRedisTemplate.opsForValue().get("name");
        Assert.assertEquals(cacheVal,"HG");
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void redisTemplate() {
       final byte[] key = "name".getBytes();
       final byte[] val = "HG".getBytes();
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {

                connection.set(key, val);
                return true;
            }
        });

        String cacheVal = redisTemplate.execute(new RedisCallback<String>() {
            @Override public String doInRedis(RedisConnection connection) throws DataAccessException {
                return new String(connection.get(key));
            }
        });

        System.out.println("cacheVal:" + cacheVal);
    }
}
