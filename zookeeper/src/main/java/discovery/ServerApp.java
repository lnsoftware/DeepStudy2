package discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;


import java.util.UUID;

/**
 * User: hupeng
 * Date: 14-9-16
 * Time: 下午8:05
 */
public class ServerApp {

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
        ServiceRegistrar serviceRegistrar = new ServiceRegistrar(client,"services");
        ServiceInstance<InstanceDetails> instance1 = ServiceInstance.<InstanceDetails>builder()
                .name("service1")
                .port(12345)
                .address("192.168.1.100")   //address不写的话，会取本地ip
                .payload(new InstanceDetails(UUID.randomUUID().toString(),"192.168.1.100",12345,"Test.Service1"))
                .uriSpec(new UriSpec("{scheme}://{address}:{port}"))
                .build();
        ServiceInstance<InstanceDetails> instance2 = ServiceInstance.<InstanceDetails>builder()
                .name("service2")
                .port(12345)
                .address("192.168.1.100")
                .payload(new InstanceDetails(UUID.randomUUID().toString(),"192.168.1.100",12345,"Test.Service2"))
                .uriSpec(new UriSpec("{scheme}://{address}:{port}"))
                .build();
        serviceRegistrar.registerService(instance1);
        serviceRegistrar.registerService(instance2);


//        System.out.println( client.);
        // 10s后卸载服务
//        Thread.sleep(10000);
//        serviceRegistrar.unregisterService(instance1);

        Thread.sleep(Integer.MAX_VALUE);
    }
}