package com.zhiyin.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hg on 2016/3/29.
 */
@Slf4j
@RestController
public class UserController {

    @RequestMapping(method = RequestMethod.GET, path = "/user-api/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "hello:" + name;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/user-api-gray/hello/{name}")
    public String grayhello(@PathVariable("name") String name) {
        return "gray:" + name;
    }

//
//
//    @RequestMapping(method = RequestMethod.POST, path = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String helloPost(@RequestBody User user) {
//        log.info("hello" + user.getName());
//        return "hello " + user.getName()+"!";
//    }


}
