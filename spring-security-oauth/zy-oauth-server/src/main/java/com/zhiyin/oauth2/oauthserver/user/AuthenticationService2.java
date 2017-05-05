package com.zhiyin.oauth2.oauthserver.user;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.zhiyin.oauth.core.UserFindType;
import com.zhiyin.oauth.core.ZyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
//@Service
public class AuthenticationService2 implements UserDetailsService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserInfo userInfo = null;
        if (!Strings.isNullOrEmpty(username) && username.startsWith("third_")) {
            try {
                userInfo = userInfoRepo.findOne(Long.valueOf(username.substring("third_".length())));
            } catch (Exception e) {
                log.error("第三方用户获取失败,{}", username, e);
            }
        } else {
            userInfo = userInfoRepo.findByTelephone(username);
        }

//        查询用户角色
//        List<UserRole> roles = userRoleRepo.findByUsername(username);

//        设置默认的角色
        List<UserRole> roles = Lists.newArrayList();
        roles.add(new UserRole("ROLE_MOBILE"));

        List<SimpleGrantedAuthority> auhorities = Lists.transform(roles, new Function<UserRole, SimpleGrantedAuthority>() {
            public SimpleGrantedAuthority apply(UserRole role) {
                return new SimpleGrantedAuthority(role.getAuthority());
            }
        });

        log.info("user info:{}; role info:{}", JSON.toJSONString(userInfo), JSON.toJSONString(roles));

        ZyUser userDetails = new ZyUser(username,
                userInfo.getPassword(), auhorities, userInfo.getId());

//        UserDetails userDetails = new User(userInfo.getUsername(),
//                userInfo.getPassword(), auhorities );

        return userDetails;
    }




    public static void main(String[] args) {
        String username = "third_111966395428864";
        System.out.println(Long.valueOf(username.substring("third_".length()))
        );
    }

} 