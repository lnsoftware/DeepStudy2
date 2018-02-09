package com.zhiyin.devtools.demo.jdi;

import org.springframework.stereotype.Component;

/**
 * Created by wangqinghui on 2018/1/7.
 */
@Component
public class UserService {

    public String getName(long id){
        return "hg-"+id;
    }
}
