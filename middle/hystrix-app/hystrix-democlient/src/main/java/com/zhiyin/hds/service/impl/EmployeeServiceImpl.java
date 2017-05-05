package com.zhiyin.hds.service.impl;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhiyin.hds.service.IEmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    //	@Value("#{'${employee.service.url}'}")
    private String employeeServiceUrl;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
            @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
            @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
            @HystrixProperty(name = "fallback.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
            @HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
            @HystrixProperty(name = "circuitBreaker.forceClosed", value = "false")}, fallbackMethod = "fallback", commandKey = "list", groupKey = "EmployeeServiceImpl", threadPoolKey = "thread-pool-employee-service", threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "5")}, ignoreExceptions = {IllegalAccessException.class})
    public String list() {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create(employeeServiceUrl + "/list");
        return restTemplate.getForObject(uri, String.class);
    }

    public String fallback() {
        return "Fallback call, seems employee service is down";
    }

}
