package com.hg.msg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hg on 2016/6/16.
 */

@Controller
public class DashboardController {

    @RequestMapping("/")
    public String home() {
        return "forward:/hystrix";
    }

}
