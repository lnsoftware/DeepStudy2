package com.zhiyin.mpush.mapper;

import com.zhiyin.mpush.entity.TestBasicData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface TestBasicDataMapper {

    public List<TestBasicData> selectInc(@Param("fromTime") Date fromTime, @Param("toTime") Date toTime);

    public List<TestBasicData> selectIncByTest(@Param("fromTime") Long fromTime, @Param("toTime") Long toTime);

}