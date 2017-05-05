package com.zhiyin.apm.heart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hg on 2016/3/29.
 */
@Slf4j
@RestController
public class HelloController {

    //    public static void main(String[] args) {
//
//        User user = new User();
//        user.setName("admin");
//        log.info(JSON.toJSONString(user));
//    }
    @Autowired
    private Environment environment;

    @RequestMapping(method = RequestMethod.GET, path = "/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "hello" + name;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String helloGet() {
        return "hello, I'm " + environment.getProperty("spring.application.name") + "!";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String helloPost(@RequestBody User user) {
        log.info("hello" + user.getName());
        return "hello " + user.getName() + "!";
    }

    @RequestMapping(value = "/ok")
    public String ok() {
        return "ok";
    }
}
