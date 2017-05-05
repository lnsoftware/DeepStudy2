package com.hg.frame.eureka;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by wangqinghui on 2017/4/5.
 */
@Controller
public class NN {

    @Autowired
    private EurekaClient eurekaClient;

    @RequestMapping("/config")
    public List<InstanceInfo> getConfigService(
            @RequestParam(value = "appId", defaultValue = "") String appId,
            @RequestParam(value = "ip", required = false) String clientIp) {
        Application application = eurekaClient.getApplication("DEMO-CONSUMER");
        List<InstanceInfo> instances = application.getInstances();
//        List<ServiceDTO> result = instances.stream().map(new Function<InstanceInfo, ServiceDTO>() {
//
//            @Override
//            public ServiceDTO apply(InstanceInfo instance) {
//                ServiceDTO service = new ServiceDTO();
//                service.setAppName(instance.getAppName());
//                service.setInstanceId(instance.getInstanceId());
//                service.setHomepageUrl(instance.getHomePageUrl());
//                return service;
//            }
//
//        }).collect(Collectors.toList());
        return instances;
    }
}
