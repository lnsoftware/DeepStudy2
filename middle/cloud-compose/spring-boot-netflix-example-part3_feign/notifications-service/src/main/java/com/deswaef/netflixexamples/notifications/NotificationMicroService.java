package com.deswaef.netflixexamples.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableEurekaClient //or @EnableDiscoveryClient
public class NotificationMicroService {

    public static void main(String[] args) {
        SpringApplication notificationMicroService = new SpringApplication(NotificationMicroService.class);
        notificationMicroService.addListeners(new ApplicationPidFileWriter("notification-micro-service.pid"));
        notificationMicroService.run(args);
    }
}
