package com.zhiyin.spring.beanregistry.scanpackage.test;

import com.zhiyin.spring.beanregistry.scanpackage.ServiceComponent;

/**
 * Created by hg on 2017/7/3.
 */
@ServiceComponent
public class UserService {

    public String hello(String name){
        return "hello "+name;
    }

}
