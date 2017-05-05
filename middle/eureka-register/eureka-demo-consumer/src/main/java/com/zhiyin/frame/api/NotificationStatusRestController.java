package com.zhiyin.frame.api;


import com.alibaba.fastjson.JSON;
import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/")
public class NotificationStatusRestController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/version")
    public String version() {
        log.info("call version");

        return notificationService.version();
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/{name}")
    public String serviceUrl(@PathVariable("name") String name) {
        List<ServiceInstance> ins = discoveryClient.getInstances(name);
        return JSON.toJSONString(ins);
    }



}