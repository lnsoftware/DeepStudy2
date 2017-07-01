package com.zhiyin.spring.ioc.dep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hg on 2017/6/29.
 */
@Service
public class SecondService {

    @Autowired
    private FirstService firstService;

    public String getName(){
        return "second";
    }

    public String hello(){
        return "";
    }

}
