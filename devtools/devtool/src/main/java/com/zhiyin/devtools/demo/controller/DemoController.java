package com.zhiyin.devtools.demo.controller;

import com.zhiyin.devtools.demo.jdi.BreakpointUtil;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class DemoController {

    @GetMapping("/")
    public String index() {
        int val = new Random().nextInt(1000);
        val = DebugPortUtil.getRemoteDebugPort();
        String hello = "sss";
        return hello + "," + val;
    }

    @GetMapping("/breakpoint")
    public String breakpoint() {
        return "breakpoint random:" + BreakpointUtil.randomVal();
    }

    @Data
    public static class UserInfo {

        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}