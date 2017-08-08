package com.zhiyin.spring.mvc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
public class UserInfoController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String queryAllBankResult() {
        return "hello";
    }

    // 不配置MessageConver时，这种写法会存在问题。FormHttpMessageConverter的canWrite仅仅支持返回值为MultiValueMap类型数据
    @RequestMapping(value = "formPost", method = RequestMethod.POST,produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public User formPost(@RequestParam("id") Long id,@RequestParam("name") String name) {
        log.info("req, userId:{}",id);
        User u = new User();
        u.setId(id);
        u.setName(name);
        return u;
    }

    @RequestMapping(value = "jsonPost", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@RequestBody User user) {
        return user;
    }

    @Data
    public static class User{
        private Long id;
        private String name;
    }
}