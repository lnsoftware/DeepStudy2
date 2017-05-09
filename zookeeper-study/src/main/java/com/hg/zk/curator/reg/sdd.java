package com.hg.zk.curator.reg;

import com.hg.zk.curator.curator.*;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceCache;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.curator.x.discovery.details.ServiceCacheListener;

/**
 * Created by wangqinghui on 2016/6/2.
 */
public class sdd {

    private static final String PATH= XServCenter.SERV_PATH;

    public  static  void main(String args[]) throws Exception {

        CuratorFramework client= CuratorFrameworkFactory.newClient("localhost:2181",new ExponentialBackoffRetry(1000, 3));

        client.start();

        JsonInstanceSerializer<XServInstance> serializer = new JsonInstanceSerializer<XServInstance>(XServInstance.class);


//        thisInstance = ServiceInstance.<XServInstance>builder()
//                .name(serviceName)
//                .payload(new XServInstance(address, info))
//                .port((int) (65535 * Math.random())) // in a real application, you'd use a common port
//                .uriSpec(uriSpec)
//                .build();

        ServiceDiscovery serviceDiscovery = ServiceDiscoveryBuilder.builder(XServInstance.class).client(client).basePath(PATH).serializer(serializer).build();

        serviceDiscovery.start();


        XServInstance xServInstance = new XServInstance();
                xServInstance.setId("serv_id_1_12");
                xServInstance.setName("serv_name_1" );
        ServiceInstance<XServInstance> serviceInstance = ServiceInstance.<XServInstance>builder()
                .id(xServInstance.getId())
                .name(xServInstance.getName())
                .payload(xServInstance).build();
        serviceDiscovery.registerService( serviceInstance );


        Thread.sleep(Integer.MAX_VALUE);



//        serviceDiscovery
//        final ServiceCache cache=serviceDiscovery.serviceCacheBuilder().name("s1").build();
//        cache.addListener(new ServiceCacheListener() {
//            @Override
//            public void stateChanged(CuratorFramework client, ConnectionState newState) {
//                System.out.println("state change.");
//            }
//            @Override
//            public void cacheChanged() {
//                System.out.println("changed:"+cache.getInstances().size());
//            }
//        });
//        cache.start();

    }
}
