package com.zhiyin.spring.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Created by wangqinghui on 2018/4/20.
 */
@Slf4j
@Data
public class Student extends User {

    private String stuNo;

    @Override
    public void ini(){
        log.info("stu ini");
    }

}
