package com.bfa.sbgl.app.api.test.controller;

/**
 * Created by wangqinghui on 2016/2/4.
 */


import com.bfa.sbgl.app.api.test.entity.User;
import com.bfa.sbgl.app.api.test.service.ITestUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/t/u")
public class TestUserController {

    private CounterService counterService;

    @Autowired
    private ITestUserService userService;

    @ApiOperation(value="Get all users",notes="requires noting")
    @RequestMapping(method=RequestMethod.GET)
    public List<User> getUsers(){
        List<User> list=new ArrayList<User>();

        User user=new User();
        user.setName("hello");
        list.add(user);

        User user2=new User();
        user.setName("world");
        list.add(user2);
        return list;
    }


//    @RequestMapping(method=RequestMethod.GET )
@RequestMapping(method = RequestMethod.GET, path = "/getAll", produces = "application/json")
public List<User> getAll(){
     return userService.selectAll();
    }

    @ApiOperation(value="Get user with id",notes="requires the id of user")
    @RequestMapping(value="/{name}",method=RequestMethod.GET)
    public User getUserById(@PathVariable String name){
        User user=new User();
        user.setName("hello world");
        return user;
    }
}