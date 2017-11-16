package com.zhiyin.aysnc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
@ServletComponentScan
public class AsynDemoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AsynDemoApplication.class, args);
    }
}