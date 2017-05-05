package com.hg.disconf.webdemo;

import com.hg.disconf.webdemo.confs.JedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * Created by wangqinghui on 2016/5/31.
 */
@Service
public class GetDisconfVal {

    @Autowired
    private JedisConfig jedisConfig;

    public String getVal(){
        return jedisConfig.getHost();
    }
}
