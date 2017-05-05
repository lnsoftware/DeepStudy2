package com.zhiyin.mpush.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.zhiyin.mpush.entity.TestBasicData;
import com.zhiyin.mpush.mapper.TestBasicDataMapper;
import com.zhiyin.mpush.service.ITestBasicDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wangqinghui on 2016/3/22.
 */
@Slf4j
@Service
public class TestBasicDataServiceImpl implements ITestBasicDataService {

    @Autowired
    TestBasicDataMapper testBasicDataMapper;


    @Override
    public PageInfo selectInc(Date fromTime, Date toTime) {
        PageHelper.startPage(1,3);

        List<TestBasicData> list = testBasicDataMapper.selectInc(fromTime,toTime);
//用PageInfo对结果进行包装
        PageInfo page = new PageInfo(list);

        return page;
    }

    @Override
    public PageInfo selectIncByTest(Long fromTime, Long toTime) {
        PageHelper.startPage(1,3);

        List<TestBasicData> list = testBasicDataMapper.selectIncByTest(fromTime,toTime);
        PageInfo page = new PageInfo(list);

        return page;
    }

}

