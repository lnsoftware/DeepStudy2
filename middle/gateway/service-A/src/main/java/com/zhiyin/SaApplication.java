package com.zhiyin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SaApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SaApplication.class).web(true).run(args);
    }

}
