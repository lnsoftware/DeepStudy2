package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

@Configuration
@PropertySource("classpath:/redis.properties")
public class RedisTemplateConfig {

    private @Value("${redis.host}") String host;
    private @Value("${redis.port}") int redisPort;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(redisPort);
        factory.setUsePool(true);
        return factory;
    }


    @Bean
    RedisTemplate redisTemplate() {
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<String,String>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean("stringRedisTemplate")
    StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

//		JDK序列化，不方便查看
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

        // json序列化，方便查看
//		redisTemplate.setValueSerializer(new JacksonJsonRedisSerializer (Object.class));

        return redisTemplate;
    }

}