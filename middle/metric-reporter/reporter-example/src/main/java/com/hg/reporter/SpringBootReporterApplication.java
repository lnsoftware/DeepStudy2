package com.hg.reporter;

import com.netflix.hystrix.examples.demo.HystrixCommandDemo;
import com.zhiyin.apm.reporter.boot.EnableReporterAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@EnableReporterAutoConfiguration
@EnableCircuitBreaker
@EnableScheduling
@SpringBootApplication
public class SpringBootReporterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootReporterApplication.class, args);
    }

}
