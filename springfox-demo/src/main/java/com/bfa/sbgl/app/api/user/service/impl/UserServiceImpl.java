package com.bfa.sbgl.app.api.user.service.impl;

import com.bfa.sbgl.app.api.user.entity.User;
import com.bfa.sbgl.app.api.user.mapper.UserMapper;
import com.bfa.sbgl.app.api.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangqinghui on 2016/2/4.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    public UserMapper userMapper;

    @Override
    public List<User> selectAll(){
        return userMapper.selectAll();
    }

    @Override
    public User selectById(){
        return userMapper.selectById();
    }

}
