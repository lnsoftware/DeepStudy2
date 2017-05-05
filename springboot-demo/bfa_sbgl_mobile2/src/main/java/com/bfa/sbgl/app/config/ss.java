//package com.bfa.sbgl.app.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by hg on 2016/2/3.
// */
//
//@Configuration
//@EnableSwagger
//@EnableAutoConfiguration
//public class SwaggerConfig
//{
//
//    private SpringSwaggerConfig springSwaggerConfig;
//
//    @Autowired
//    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
//        this.springSwaggerConfig = springSwaggerConfig;
//    }
//
//
//    public SwaggerSpringMvcPlugin customImplementation(){
//        return  new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
//                .apiInfo(new ApiInfo("api", "desc", null, null, null, null))
//                .useDefaultResponseMessages(false)
//                .includePatterns("/users.*");
//    }
//}