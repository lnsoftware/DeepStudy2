import org.apache.curator.test.TestingServer;

/**
 * 嵌入式ZK
 * Created by wangqinghui on 2016/5/18.
 */
//http://stackoverflow.com/questions/9286054/is-it-possible-to-start-a-zookeeper-server-instance-in-process-say-for-unit-tes
public class ZkEmbedServer {

    public static void main(String[] args) throws Exception {

        TestingServer zkTestServer = new TestingServer(2181);

        Thread thread = new Thread() {
            public void run() {
                System.out.println("thread start!");
            }
        };

        thread.setDaemon(true);
        thread.start();

    }

}
