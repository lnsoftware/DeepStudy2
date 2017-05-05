package com.zhiyin.frame.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangqinghui on 2017/1/16.
 */
@RestController
public class Demo {
    @RequestMapping("/hello")
    public String home() {
        return "Hello World";
    }
}
