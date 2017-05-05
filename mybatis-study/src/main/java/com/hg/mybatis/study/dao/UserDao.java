package com.hg.mybatis.study.dao;

import com.hg.mybatis.study.entity.User;

import java.util.List;

/**
 * Created by wangqinghui on 2015/12/23.
 */

public interface UserDao {

    public void insert(User user);

    public User findUserById (int userId);

    public List<User> findAllUsers();

}
