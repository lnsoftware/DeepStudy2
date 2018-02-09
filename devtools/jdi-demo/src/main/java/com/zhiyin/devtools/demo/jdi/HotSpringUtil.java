package com.zhiyin.devtools.demo.jdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangqinghui on 2018/1/7.
 */
@Component
public class HotSpringUtil {

    @Autowired
    private UserService userService;

    public String getUserName(long id){
        return userService.getName(id);
    }

//    public String getUserName(long id){
//        return "static-"+id;
//    }

}
