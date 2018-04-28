package com.zhiyin.spring.service;

import lombok.Data;

/**
 * Created by wangqinghui on 2018/4/20.
 */
@Data
public class UserServiceImpl implements UserService {

    private String rootName;

    @Override
    public String hello(){
        return "hg";
    }
}
