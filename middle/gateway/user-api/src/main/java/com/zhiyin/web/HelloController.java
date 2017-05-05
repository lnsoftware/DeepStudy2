package com.zhiyin.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hg on 2016/3/29.
 */
@Slf4j
@RestController
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String hello() {
        return "hello, I'm user-api.";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello/{name}")
    public String helloName(@PathVariable("name") String name) {
        return "hello" + name;
    }


    @RequestMapping(method = RequestMethod.POST, path = "/hello")
    public String helloPost(@RequestBody String name) {
        return "hello" + name;
    }


    @RequestMapping(value = "/ok")
    public String ok() {
        return "ok";
    }

}
