package com.bfa.sbgl.app.api.security.mapper;


import com.bfa.sbgl.app.api.security.entity.AuthRole;

import java.util.Set;

/**
 * Created by zl on 2015/8/27.
 */
public interface AuthUserRoleMapper { //extends MyMapper<User>

    public Set<AuthRole> selectByUser(Long userId);

}
