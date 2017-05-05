//package com.zhiyin.gateway;
//
//import com.zhiyin.gateway.filter.AccessFilter2;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.web.SpringBootServletInitializer;
//import org.springframework.cloud.client.SpringCloudApplication;
//import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
//import org.springframework.context.annotation.Bean;
//
//@EnableZuulProxy
//@SpringCloudApplication
//public class GatewayApplication2 extends SpringBootServletInitializer {
//
//    public static void main(String[] args) {
//        SpringApplication.run(GatewayApplication.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(GatewayApplication.class);
//    }
//
//    @Bean
//    public AccessFilter2 accessFilter() {
//        return new AccessFilter2();
//    }
//
//}
