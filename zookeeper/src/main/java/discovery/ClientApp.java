package discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.ServiceInstance;

/**
 * User: hupeng
 * Date: 14-9-16
 * Time: 下午8:16
 */
public class ClientApp {

    public static void main(String[] args) throws Exception {

       String zkServer =  "127.0.0.1:2181";
//        zkServer = "122.119.127.33:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient( zkServer , new ExponentialBackoffRetry(1000, 3));

        client = CuratorFrameworkFactory.builder()
                .connectString(zkServer)
                .sessionTimeoutMs(1000)
                .connectionTimeoutMs(1000)

                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();

        client.start();
        ServiceDiscoverer serviceDiscoverer = new ServiceDiscoverer(client,"services");

        for(int i = 0; i<1000;i ++){
            ServiceInstance<InstanceDetails> instance1 = serviceDiscoverer.getInstanceByName("service1");

            if(instance1 == null){
                System.out.println("null");
                Thread.sleep(2000);
                continue;
            }
            System.out.println(instance1.buildUriSpec());
            System.out.println(instance1.getPayload());
            Thread.sleep(2000);
        }

        serviceDiscoverer.close();
        CloseableUtils.closeQuietly(client);
    }
}