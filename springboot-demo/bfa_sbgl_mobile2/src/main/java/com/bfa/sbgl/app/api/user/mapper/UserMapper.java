package com.bfa.sbgl.app.api.user.mapper;


import com.bfa.sbgl.app.api.user.entity.User;

import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */
public interface UserMapper  { //extends MyMapper<User>
    public User selectById();
    public List<User> selectAll();
}
