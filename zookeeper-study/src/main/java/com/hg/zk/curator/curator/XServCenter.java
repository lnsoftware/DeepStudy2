package com.hg.zk.curator.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.curator.x.discovery.strategies.RoundRobinStrategy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class XServCenter {
    public static final String SERV_PATH = "/myServices";
    ServiceDiscovery<XServInstance> serviceDiscovery;
    Map<String, ServiceProvider> providerMap = new HashMap<String, ServiceProvider>();

    public void init(CuratorFramework client) throws Exception {
        JsonInstanceSerializer<XServInstance> serializer = new JsonInstanceSerializer<XServInstance>(XServInstance.class);
        serviceDiscovery = ServiceDiscoveryBuilder.builder(XServInstance.class)
                .basePath(SERV_PATH)
                .client(client)
                .serializer(serializer)
                .build();
        serviceDiscovery.start();
    }

    public void regist(XServInstance instance) throws Exception {
        ServiceInstance<XServInstance> serviceInstance = ServiceInstance.<XServInstance>builder()
                .id(instance.getId())
                .name(instance.getName())
                .payload(instance).build();
        serviceDiscovery.registerService(serviceInstance);

    }

    public void list() throws Exception {
        Collection<String> servNames = serviceDiscovery.queryForNames();
        for (String servName : servNames) {
            Collection<ServiceInstance<XServInstance>> instances = serviceDiscovery.queryForInstances("/" + servName);
            System.out.println(servName);
            for (ServiceInstance<XServInstance> instance : instances) {
                System.out.println("------- : " + instance.getId());
            }

        }
    }

    public void getServiceByName(String servName) throws Exception {
        ServiceProvider servPro = providerMap.get(servName);
        if (servPro == null) {
            servPro = serviceDiscovery.serviceProviderBuilder()
                    .serviceName(servName) //RandomStrategy StickyStrategy
                    .providerStrategy(new RoundRobinStrategy<XServInstance>())
                    .build();
            servPro.start();
            providerMap.put(servName, servPro);
        }
        ServiceInstance<XServInstance> serviceInstance = servPro.getInstance();
        if(serviceInstance != null){
                    System.out.println("getServiceByName  " + serviceInstance.getId());
        }else{
            System.out.println("null");
        }
    }
}
