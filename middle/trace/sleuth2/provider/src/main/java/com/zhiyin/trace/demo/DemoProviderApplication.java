package com.zhiyin.trace.demo;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@RestController
public class DemoProviderApplication {

    @Autowired
    private Tracer tracer;

    @RequestMapping("/")
    public String home() {
        return "I'm provider.";
    }

    @RequestMapping("/curTime")
    public String curTime() {
        Span dateServiceSpan = tracer.createSpan("currentTimeService");
        try {
            log.info("get now time str.");
            return DateTime.now().toString();
        } catch (Exception e) {

        } finally {
            tracer.close(dateServiceSpan);
        }
        return "";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoProviderApplication.class, args);
    }
}
