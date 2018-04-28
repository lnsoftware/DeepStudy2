package com.zhiyin.spring.aop.demo4.service.impl;

import com.zhiyin.spring.aop.demo4.service.UserService;

/**
 * Created by wangqinghui on 2018/4/21.
 */
public class UserServiceImpl implements UserService {

    @Override
    public String hello(){
        return "hg";
    }

    @Override
    public String getById(long id){
        return "hg-"+id;
    }
}
