package com.zhiyin.spring.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Created by wangqinghui on 2018/4/20.
 */
@Slf4j
@Data
public class User {

    private String name;
    private String password;
    private Date date;
    private String systemMark;

    public void ini(){
        log.info("ini");
    }

}
