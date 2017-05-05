package com.zhiyin.rpc.shi.demo.action;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DemoController {
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, HttpServletRequest request,
                               HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        model.addAttribute("message", "商家详细查询");
        return "helloWorld";
    }

    @RequestMapping("/qq.htm")
    public String tabList(ModelMap model) {

        model.addAttribute("message", "Spring 3 MVC Hello World !");
        return "helloWorld";
    }
}