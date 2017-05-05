package com.zhiyin.mpush.service;

import com.github.pagehelper.PageInfo;

import java.util.Date;

/**
 * Created by wangqinghui on 2016/4/7.
 */
public interface ITestBasicDataService {

    public PageInfo selectInc(Date fromTime, Date toTime);



    PageInfo selectIncByTest(Long fromTime, Long toTime);
}
