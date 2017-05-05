//package com.hg.spring.cache.rediscache;
//
//import org.springframework.cache.Cache;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.RedisSerializer;
//
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import redis.clients.jedis.JedisPoolConfig;
//
//public class RedisCachePerformance {
//    private static final String CACHE_NAME = "TEST_CACHE";
//
//    public static void main(final String[] args) throws Exception {
//        final String redisHost = "localhost";
//        final int redisPort = 6379;
//
//        final int threads = 40;
//        final int iterations = 10000;
//
//        final RedisTemplate<?, ?> template = createRedisTemplate(redisHost, redisPort);
//        final RedisCacheManager manager = new RedisCacheManager(template);
//        final Cache cache = manager.getCache(CACHE_NAME);
//
//        final long timeCache = performTestCacheGet(cache, threads, iterations);
//        System.out.println("Cache 'get' time: " + timeCache + "ms");
//
//        final long timeTemplate = performTestTemplateGet(template, threads, iterations);
//        System.out.println("Template 'get' time: " + timeTemplate + "ms");
//    }
//
//    /**
//     * Multiple threads repeatedly get from the cache.
//     */
//    private static long performTestCacheGet(
//            final Cache cache,
//            final int threadCount,
//            int iterations) throws Exception {
//        final String key = "key";
//        final String val = "value";
//
//        // Initial put into the cache.
//        cache.put(key, val);
//
//        final List<Thread> threads = new ArrayList<>(threadCount);
//        for (int thread=0; thread < threadCount; ++thread) {
//            boolean add = threads.add(new Thread(
//            public void run(){
//                for (int i=0; i<iterations; ++i) {
//                    cache.get(key);
//                }
//            }));
//        }
//
//        final long start = System.currentTimeMillis();
//        for (final Thread t : threads) {
//            t.start();
//        }
//        for (final Thread t : threads) {
//            t.join();
//        }
//        return System.currentTimeMillis() - start;
//    }
//
//    /**
//     * Perform same operations as cache above, but using RedisTemplate directly.
//     */
//    private static long performTestTemplateGet(
//            final RedisTemplate<?, ?> template,
//            final int threadCount,
//            final int iterations) throws Exception {
//        final byte[] prefix = CACHE_NAME.getBytes(StandardCharsets.UTF_8);
//        final String key = "key";
//        final String val = "value";
//
//        // Initial put into the cache.
//        template.execute((RedisCallback<Void>) conn -> {
//            final byte[] keyBytes = serializeKey(template, prefix, key);
//            final byte[] valBytes = serializeValue(template, val);
//            conn.multi();
//            conn.set(keyBytes, valBytes);
//            conn.expire(keyBytes, 60000);
//            conn.exec();
//            return null;
//        });
//
//        final List<Thread> threads = new ArrayList<>(threadCount);
//        for (int thread=0; thread < threadCount; ++thread) {
//            threads.add(new Thread(() -> {
//              for (int i=0; i<iterations; ++i) {
//                  // Perform serialization of key before entering execution block.
//                  final byte[] keyBytes = serializeKey(template, prefix, key);
//                  final byte[] valBytes = template.execute((RedisCallback<byte[]>) conn -> {
//                      return conn.get(keyBytes);
//                  });
//                  // Perform deserialization of value after exiting execution block.
//                  /*final Object valueFromRedis =*/ deserializeValue(template, valBytes);
//              }
//            }));
//        }
//
//        final long start = System.currentTimeMillis();
//        for (final Thread t : threads) {
//            t.start();
//        }
//        for (final Thread t : threads) {
//            t.join();
//        }
//        return System.currentTimeMillis() - start;
//    }
//
//    private static byte[] serializeKey(final RedisTemplate<?, ?> template, final byte[] prefix, final Object key) {
//        @SuppressWarnings("unchecked")
//        final byte[] keyBytes = ((RedisSerializer<Object>) template.getKeySerializer()).serialize(key);
//        byte[] fullKeyBytes = Arrays.copyOf(prefix, prefix.length + keyBytes.length);
//        System.arraycopy(keyBytes, 0, fullKeyBytes, prefix.length, keyBytes.length);
//        return fullKeyBytes;
//    }
//
//    @SuppressWarnings("unchecked")
//    private static byte[] serializeValue(final RedisTemplate<?, ?> redisTemplate, final Object val) {
//        return ((RedisSerializer<Object>) redisTemplate.getValueSerializer()).serialize(val);
//    }
//
//    @SuppressWarnings("unchecked")
//    private static Object deserializeValue(final RedisTemplate<?, ?> redisTemplate, final byte[] valBytes) {
//        return ((RedisSerializer<Object>) redisTemplate.getValueSerializer()).deserialize(valBytes);
//    }
//
//    public static RedisTemplate<?, ?> createRedisTemplate(
//            final String host, final int port) {
//        final JedisPoolConfig poolConfig = new JedisPoolConfig();
//
//        final JedisConnectionFactory connectionFactory =
//                new JedisConnectionFactory(poolConfig);
//        connectionFactory.setHostName(host);
//        connectionFactory.setPort(port);
//        connectionFactory.afterPropertiesSet();
//
//        final RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(connectionFactory);
//        redisTemplate.afterPropertiesSet();
//
//        return redisTemplate;
//    }
//}