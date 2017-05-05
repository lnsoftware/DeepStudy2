import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.core.RBlockingQueue;
import org.redisson.core.RBucket;
import org.redisson.core.RList;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangqinghui on 2016/6/7.
 */
public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        //创建配置信息
        Config config = new Config();
        config.useSingleServer().setAddress("localhost:6379").setConnectionPoolSize(5);

        RedissonClient redisson = Redisson.create(config);

        RBlockingQueue<String> queue = redisson.getBlockingQueue("anyQueue");
        queue.offer( "add1" );
        queue.offer( "add2" );


//        String ob = queue.poll(10, TimeUnit.SECONDS);

        while (true){
            String ob = queue.take();
            System.out.println(ob);
        }


//        redisson.shutdown();
    }


}
