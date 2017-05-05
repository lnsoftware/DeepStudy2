package com.zhiyin.notifications.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangqinghui on 2016/12/24.
 */

@Slf4j
@RestController
@RequestMapping("")
public class HelloController {


    @RequestMapping("/hello")
    public String version() {
        log.info("call hello");

        return "hello";
    }
}
