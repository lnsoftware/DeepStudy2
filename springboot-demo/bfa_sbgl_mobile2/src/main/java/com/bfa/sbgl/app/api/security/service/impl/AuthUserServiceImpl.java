package com.bfa.sbgl.app.api.security.service.impl;

import com.bfa.sbgl.app.api.security.entity.AuthRole;
import com.bfa.sbgl.app.api.security.entity.AuthUser;
import com.bfa.sbgl.app.api.security.mapper.AuthUserMapper;
import com.bfa.sbgl.app.api.security.mapper.AuthUserRoleMapper;
import com.bfa.sbgl.app.api.security.service.IAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("authUserService")
public class AuthUserServiceImpl implements IAuthUserService {

	@Autowired
	private AuthUserMapper authUserMapper;

	@Autowired
	private AuthUserRoleMapper authUserRoleMapper;



    @Override
    public AuthUser selectByName(String name){
        AuthUser u = authUserMapper.selectByName(name);
        if(u == null || u.getId() <=0){
            return null;
        }
        Set<AuthRole> role = authUserRoleMapper.selectByUser(u.getId());
        u.setRoles(role);

        return u;
    }

}