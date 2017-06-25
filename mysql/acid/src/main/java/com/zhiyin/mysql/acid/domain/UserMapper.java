package com.zhiyin.mysql.acid.domain;

import java.util.List;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE NAME = #{name}")
    User findByName(@Param("name") String name);

//    @Insert("INSERT INTO users(NAME, AGE) VALUES(#{name}, #{age})")
//    int insert(@Param("name") String name, @Param("age") Integer age);

    @Insert("INSERT INTO users(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(User user);

    @Select("SELECT * FROM users")
    List<User> selectAll();



}