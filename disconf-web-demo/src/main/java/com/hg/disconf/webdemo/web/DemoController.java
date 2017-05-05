package com.hg.disconf.webdemo.web;

import com.hg.disconf.webdemo.confs.CodeConfig;
import com.hg.disconf.webdemo.confs.JedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangqinghui on 2016/3/11.
 */


@Controller
public class DemoController {

    @Autowired
    CodeConfig codeConfig;

    @Autowired
    JedisConfig jedisConfig;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/conf"  )
    public String greeting() {

        String str = codeConfig.getCodeError();

        str = jedisConfig.getHost() + ":" + jedisConfig.getPort();

        return str;
    }


}