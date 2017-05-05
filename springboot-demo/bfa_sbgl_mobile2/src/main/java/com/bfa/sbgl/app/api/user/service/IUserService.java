package com.bfa.sbgl.app.api.user.service;

import com.bfa.sbgl.app.api.user.entity.User;

import java.util.List;

/**
 * Created by wangqinghui on 2016/2/4.
 */
public interface IUserService {
    List<User> selectAll();

    User selectById();
}
