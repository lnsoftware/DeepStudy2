package com.bfa.sbgl.app.api.test.service.impl;

import com.bfa.sbgl.app.api.test.entity.User;
import com.bfa.sbgl.app.api.test.mapper.TestUserMapper;
import com.bfa.sbgl.app.api.test.service.ITestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangqinghui on 2016/2/4.
 */
@Service("testUserService")
public class TestUserServiceImpl implements ITestUserService {

    @Autowired
    public TestUserMapper testUserMapper;

    @Override
    public List<User> selectAll(){
        return testUserMapper.selectAll();
    }

    @Override
    public User selectById(){
        return testUserMapper.selectById();
    }

}
