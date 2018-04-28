package com.zhiyin;

import com.google.common.base.Strings;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

/**
 * Created by wangqinghui on 2018/3/28.
 */
@DefaultProperties(defaultFallback = "fallbackGetUserId" )
@Service
public class UserService {

    @HystrixCommand(commandKey = "getUserId")
    public long getUserId(String name){
        if(Strings.isNullOrEmpty(name)){
            throw new RuntimeException();
        }
        return System.currentTimeMillis();
    }

    public long fallbackGetUserId(){
        return 0L;
    }
}
