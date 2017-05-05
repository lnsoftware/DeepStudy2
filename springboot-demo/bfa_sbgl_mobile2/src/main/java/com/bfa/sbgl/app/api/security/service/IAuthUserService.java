package com.bfa.sbgl.app.api.security.service;

import com.bfa.sbgl.app.api.security.entity.AuthUser;

/**
 * Created by wangqinghui on 2016/2/5.
 */
public interface IAuthUserService {
    AuthUser selectByName(String name);
}
