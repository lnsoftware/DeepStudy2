package com.bfa.sbgl.app.api.test.mapper;


import com.bfa.sbgl.app.api.test.entity.User;

import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */
public interface TestUserMapper  { //extends MyMapper<User>
    public User selectById();
    public List<User> selectAll();

    public User selectByName();
}
