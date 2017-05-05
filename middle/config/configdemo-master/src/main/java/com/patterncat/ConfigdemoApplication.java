package com.patterncat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.patterncat"})
public class ConfigdemoApplication {

//    @Value("${spring.cloud.zookeeper.connectString}")
//    String zkConnString;
//
//    @Bean
//    public ZookeeperProperties zkProperties() {
//        ZookeeperProperties properties =  new ZookeeperProperties();
//        properties.setConnectString(zkConnString);
//        return properties;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigdemoApplication.class, args);
    }
}
