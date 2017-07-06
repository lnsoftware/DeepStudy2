package com.zhiyin.mysql.lock.domain;

import org.apache.ibatis.annotations.*;

@Mapper
public interface OptimisticUserMapper {

    @Select("select * from optimistic_user where id=#{id}")
    OptimisticUser selectById(@Param("id") Long id);

    @Update("update optimistic_user set value=#{value},version=version+1 where id=#{id} and version=#{version}")
//    OptimisticUser updateOptimistic(@Param("id") Long id,@Param("value") String value ,@Param("version") Integer version);
    Integer updateOptimistic(OptimisticUser user);

}