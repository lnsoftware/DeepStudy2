package com.zhiyin.oauth2.oauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
//@EnableAutoConfiguration(exclude = {org.activiti.spring.boot.SecurityAutoConfiguration.class})

@SpringBootApplication
public class OauthServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OauthServerApplication.class, args);
    }

}