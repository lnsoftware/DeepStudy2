package com.hg.spring.cache.rediscache.controller;

import com.alibaba.fastjson.JSON;
import com.hg.spring.cache.rediscache.entity.User;
import com.hg.spring.cache.rediscache.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/us/")
public class DemoController {

    @Autowired
    private IUserInfoService userInfoService;


    @RequestMapping("/test2")
    public String testCache(){
        List<User> info = userInfoService.selectAll();

        return JSON.toJSONString(info);
    }

    @RequestMapping("/{id}")
    public String testCache(@PathVariable("id") Long id){
        User info = userInfoService.findById(id);

        return JSON.toJSONString(info);
    }


}
