import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

/**
 * Created by hg on 2017/7/11.
 */
public class Curator {

    public static void main(String[] args) throws Exception {
        String path = "/test_path";

        CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("test:2181").namespace("/test1")
            .retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000))
            .connectionTimeoutMs(5000).build();

// start
        client.start();

// create a node
        client.create().forPath("/head", new byte[0]);

// delete a node in background
        client.delete().inBackground().forPath("/head");

// create a EPHEMERAL_SEQUENTIAL
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/head/child", new byte[0]);

// get the data
        client.getData().watched().inBackground().forPath("/test");

// check the path exits
        client.checkExists().forPath(path);
    }
}
