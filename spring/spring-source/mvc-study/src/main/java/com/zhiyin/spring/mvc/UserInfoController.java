package com.zhiyin.spring.mvc;

import com.zhiyin.spring.mvc.model.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
public class UserInfoController {

    @GetMapping("/")
    public String hello( ) {
        return "hello";
    }


    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String queryAllBankResult() {
        return "hello";
    }

    // 不配置MessageConver时，这种写法会存在问题。FormHttpMessageConverter的canWrite仅仅支持返回值为MultiValueMap类型数据
    @RequestMapping(value = "formPost", method = RequestMethod.POST,produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public User formPost(@RequestParam("id") Long id, @RequestParam("name") String name) {
        log.info("req, userId:{}",id);
        User u = new User();
        u.setId(id);
        u.setName(name);
        return u;
    }

    @RequestMapping(value = "jsonPost", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@RequestBody User user) {
        log.info("req:"+user);
        return user;
    }

    @RequestMapping(value = "postList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUser(@RequestBody List<User> userList) {
        log.info("req:"+userList);
        for (User user : userList) {
            System.out.println(user.getId());
            System.out.println(user.getName());
        }
        return "";
    }

    @RequestMapping(value = "postMap", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUser(@RequestBody Map<String,User> userMap) {
        log.info("req:"+userMap);

        return userMap.toString();
    }

    @RequestMapping(value = "postComplex", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String postComplex(@RequestBody Map<String,List<User>> mapList) {
        log.info("req:"+mapList);

        return mapList.toString();
    }
}