package com.bfa.sbgl.app.api.test.service;

import com.bfa.sbgl.app.api.test.entity.User;

import java.util.List;

/**
 * Created by wangqinghui on 2016/2/4.
 */
public interface ITestUserService {
    List<User> selectAll();

    User selectById();
}
