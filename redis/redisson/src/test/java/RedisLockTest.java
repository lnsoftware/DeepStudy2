import lombok.extern.slf4j.Slf4j;
import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.core.RBucket;
import org.redisson.core.RList;
import org.redisson.core.RReadWriteLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2016/6/7.
 */
@Slf4j
public class RedisLockTest {

    public static void main(String[] args) throws InterruptedException {
        //创建配置信息
        Config config = new Config();
        config.useSingleServer().setAddress("localhost:6379").setConnectionPoolSize(5);

        RedissonClient redisson = Redisson.create(config);

        RReadWriteLock lock = redisson.getReadWriteLock("lock_test");

        log.info("start lock.");
        lock.writeLock().lock(10, TimeUnit.SECONDS);

        Thread.sleep(50000);

        log.info("end lock");
        lock.writeLock().unlock();


        redisson.shutdown();
    }
}
