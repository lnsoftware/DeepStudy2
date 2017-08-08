package com.zhiyin.mybatis.boot.mapper;

import com.zhiyin.mybatis.boot.entity.OrderTask;
import com.zhiyin.mybatis.boot.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderTaskMapper {

    public int add(OrderTask orderTask);

    public List<OrderTask> selectAll();


    public List<Long> selectNotIn(@Param("status") Integer status,@Param("notInIdList") List<Long> notInIdList,@Param("deadline") Date deadline);

    public List<Long> selectNotIn2(@Param("status") Integer status,@Param("notInIdList") List<Long> notInIdList,@Param("deadline") Date deadline);

    public List<Long> selectNotIn3(@Param("status") Integer status,@Param("notInIdList") List<Long> notInIdList);

}
