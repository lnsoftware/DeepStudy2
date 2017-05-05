package com.hg.docker.timezone;

import com.alibaba.fastjson.JSON;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication
public class SpringBootReporterApplication {
    public static void main(String[] args) {
        System.out.println("当前TimeZone："+JSON.toJSONString(TimeZone.getDefault()));
        System.out.println("当前Time:"+ DateTime.now().toString());
        //SpringApplication.run(SpringBootReporterApplication.class, args);
    }
}
