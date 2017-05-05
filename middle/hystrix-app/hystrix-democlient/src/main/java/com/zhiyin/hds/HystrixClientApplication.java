package com.zhiyin.hds;

import com.zhiyin.hds.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableCircuitBreaker
@SpringBootApplication
@RestController
@EnableHystrixDashboard
@EnableFeignClients
public class HystrixClientApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HystrixClientApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HystrixClientApplication.class);
    }


    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/list")
    public String list() {
        return employeeService.list();
    }


}