package com.zhiyin.oauth2.oauthserver.user;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.zhiyin.oauth.core.ZyUser;
import com.zhiyin.oauth.core.ZyUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserInfoRepo userInfoRepo;

	@Autowired
	private UserRoleRepo userRoleRepo;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserInfo userInfo = userInfoRepo.getUserInfo(username);

        List<UserRole> roles = userRoleRepo.findByUsername(username);

//        设置默认的角色
//        List<UserRole> roles =Lists.newArrayList();
//        roles.add(new UserRole("ROLE_ADMIN"));

        List<SimpleGrantedAuthority> auhorities = Lists.transform(roles, new Function<UserRole, SimpleGrantedAuthority>() {
            public SimpleGrantedAuthority apply(UserRole role) {
                return new SimpleGrantedAuthority(role.getAuthority());
            }
        });

        log.info("user info:{}; role info:{}",JSON.toJSONString(userInfo),JSON.toJSONString(roles));

		ZyUser userDetails = new ZyUser(userInfo.getUsername(),
				userInfo.getPassword(),auhorities,userInfo.getId() );

//        UserDetails userDetails = new User(userInfo.getUsername(),
//                userInfo.getPassword(), auhorities );

		return userDetails;
	}
} 