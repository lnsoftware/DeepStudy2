package com.zhiyin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurakaServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurakaServerApplication.class).web(true).run(args);
    }

}
