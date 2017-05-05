package com.zhiyin.trace.demo;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@SpringBootApplication
@RestController
public class DemoServiceApplication {

    @RequestMapping("/")
    public String home() {
        log.info("Request to / endpoint");
        return "Hello World";
    }

    @RequestMapping("/getTime")
    public String getTime() {
        log.info("Request to /getTime endpoint");
        return getTimeAsString();
    }

    private String getTimeAsString() {
        log.info("Returning time as String");
        return DateTime.now().toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoServiceApplication.class,args);
    }
}
