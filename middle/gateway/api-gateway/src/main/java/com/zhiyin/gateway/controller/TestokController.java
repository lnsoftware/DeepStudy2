package com.zhiyin.gateway.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hg on 2016/3/29.
 */
@Slf4j
@RestController
public class TestokController {

    @Autowired
    private DiscoveryClient discoveryClient;

    //    @RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/testok")
    public String greeting() {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("admin2");
        return helloWorldCommand.execute();
    }

    @RequestMapping(value = "/test2")
    public String doDiscoveryService() {
        StringBuilder buf = new StringBuilder();
        List<String> serviceIds = discoveryClient.getServices();
        if (!CollectionUtils.isEmpty(serviceIds)) {
            for (String s : serviceIds) {
                System.out.println("serviceId:" + s);
                List<ServiceInstance> serviceInstances = discoveryClient.getInstances(s);
                if (!CollectionUtils.isEmpty(serviceInstances)) {
                    for (ServiceInstance si : serviceInstances) {
                        buf.append("[" + si.getServiceId() + " host=" + si.getHost() + " port=" + si.getPort() + " uri=" + si.getUri() + "]");
                    }
                } else {
                    buf.append("no service.");
                }
            }
        }


        return buf.toString();
    }

    class HelloWorldCommand extends HystrixCommand<String> {

        final Logger logger = LoggerFactory.getLogger(HelloWorldCommand.class);

        private final String name;

        public HelloWorldCommand(String name) {
            super(HystrixCommandGroupKey.Factory.asKey("default"));
            this.name = name;
        }

        @Override
        protected String run() throws Exception {
            logger.info("HelloWorld Command Invoked");
            return "Hello " + name;
        }
    }


}
