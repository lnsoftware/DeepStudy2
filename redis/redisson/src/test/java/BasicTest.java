import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.core.RBucket;
import org.redisson.core.RList;
import org.redisson.core.RReadWriteLock;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2016/6/7.
 */
public class BasicTest {

    public static void main(String[] args){
        //创建配置信息
        Config config = new Config();
        config.useSingleServer().setAddress("localhost:6379").setConnectionPoolSize(5);

        RedissonClient redisson = Redisson.create(config);

        RBucket<String> bucket = redisson.getBucket("anyObject");
        bucket.set("test string");
        String getStr = bucket.get();

        System.out.println(getStr);

//测试 list
        RList<String> strList = redisson.getList("strList");
        strList.clear(); //清除所有数据
        strList.add("测试中文1");
        strList.add("test2");

        RList<String> ff = redisson.getList("strList");
        System.out.println( ff.get(0) );

        redisson.shutdown();
    }
}
