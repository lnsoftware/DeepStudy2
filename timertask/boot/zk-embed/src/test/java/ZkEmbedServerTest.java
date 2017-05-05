import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.test.TestingServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wangqinghui on 2016/5/18.
 */
public class ZkEmbedServerTest {

    private TestingServer zkTestServer;

    private CuratorFramework cli;

    @Before
    public void startZookeeper() throws Exception {
        zkTestServer = new TestingServer(2181);


        cli = CuratorFrameworkFactory.newClient(zkTestServer.getConnectString(), new RetryOneTime(2000));
        cli.start();
    }

    @Test
    public void test() throws Exception {

        cli.create().forPath("/a1", "testvalue".getBytes());

    }

    @After
    public void stopZookeeper() throws IOException {
        cli.close();
//        zkTestServer.stop();
    }
}
