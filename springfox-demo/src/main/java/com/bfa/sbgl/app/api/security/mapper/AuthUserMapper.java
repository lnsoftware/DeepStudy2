package com.bfa.sbgl.app.api.security.mapper;


import com.bfa.sbgl.app.api.security.entity.AuthUser;

/**
 * Created by zl on 2015/8/27.
 */
public interface AuthUserMapper { //extends MyMapper<User>
    public AuthUser selectByName(String name);

}
