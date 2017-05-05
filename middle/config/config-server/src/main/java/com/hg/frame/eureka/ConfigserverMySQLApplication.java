package com.hg.frame.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableDbConfigServer;

@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableDbConfigServer
@SpringBootApplication
public class ConfigserverMySQLApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigserverMySQLApplication.class, args);
    }
}
