import org.junit.Test;
import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.core.RReadWriteLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class RedisReentrantLockTemplateTest {

   static int count = 0;


    public static void main(String[] args) throws InterruptedException {


        //创建配置信息
        Config config = new Config();
        config.useSingleServer().setAddress("localhost:6379").setConnectionPoolSize(500);

        RedissonClient redisson = Redisson.create(config);
        final RReadWriteLock lock = redisson.getReadWriteLock("lock_test");


        int size=20;
        final CountDownLatch startCountDownLatch = new CountDownLatch(1);
        final CountDownLatch endDownLatch=new CountDownLatch(size);
        for (int i =0;i<size;i++){
            new Thread() {

                public void run() {


//                    System.out.println("ready" +Thread.currentThread());

//                    try {
//                        startCountDownLatch.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    lock.writeLock().lock();

                    count++ ;
//                    lock.writeLock().lock();
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    lock.writeLock().unlock();
//
                    System.out.println("end" +Thread.currentThread());

//                    endDownLatch.countDown();

                }
            }.start();
        }

        System.out.println(startCountDownLatch.getCount());
//        startCountDownLatch.countDown();
//        endDownLatch.await();

        Thread.sleep(200000);
        System.out.println("endcount" +count);
    }
}