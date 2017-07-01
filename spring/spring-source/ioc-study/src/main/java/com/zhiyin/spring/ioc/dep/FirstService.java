package com.zhiyin.spring.ioc.dep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hg on 2017/6/29.
 */
@Service
public class FirstService {

    @Autowired
    private SecondService secondService;

    public FirstService(){

    }

    public String hello(String name){
        return secondService.getName() + " say: hello " + name;
    }

}
