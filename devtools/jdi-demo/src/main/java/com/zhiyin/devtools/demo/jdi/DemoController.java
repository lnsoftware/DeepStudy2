package com.zhiyin.devtools.demo.jdi;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class DemoController {

    @Autowired
    private HotSpringUtil hotSpringUtil;

    @GetMapping("/breakpoint")
    public String breakpoint() {
        return "breakpoint random:" + BreakpointUtil.randomVal();
    }


    @GetMapping("/hotswap")
    public String hotswap() {
        return "hotswap random:" + HotswapUtil.randomVal();
    }

    @GetMapping("/hotspring")
    public String hotspring() {
        return "hotspring:" + hotSpringUtil.getUserName(1);
    }


}