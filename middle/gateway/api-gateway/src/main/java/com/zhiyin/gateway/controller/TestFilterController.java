package com.zhiyin.gateway.controller;

import com.zhiyin.gateway.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hg on 2016/3/29.
 */
@Slf4j
@RestController("/local/test/")
public class TestFilterController {


    @RequestMapping(method = RequestMethod.GET, path = "/get")
    public String hello(@PathVariable("name") String name) {
        return "hello" + name;
    }


    @RequestMapping(method = RequestMethod.POST, path = "/post", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String helloPost(@RequestBody User user) {
        log.info("hello" + user.getName());
        return "hello " + user.getName() + "!";
    }


}
