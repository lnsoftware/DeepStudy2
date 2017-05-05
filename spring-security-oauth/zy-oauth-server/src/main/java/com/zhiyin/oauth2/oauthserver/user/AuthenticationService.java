package com.zhiyin.oauth2.oauthserver.user;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.zhiyin.oauth.core.UserFindType;
import com.zhiyin.oauth.core.UserLoginType;
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
@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserInfo userInfo = selectUserInfo(username);

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

        log.info("username:{}, user info:{}; role info:{}",username, JSON.toJSONString(userInfo), JSON.toJSONString(roles));

        ZyUser userDetails = new ZyUser(username,
                userInfo.getPassword(), auhorities, userInfo.getId());

        return userDetails;
    }

    public UserInfo selectUserInfo(String username) {
        UserLoginType loginType = UserLoginType.loginType(username);
        String val = loginType.findVal(username);
        log.info("user:{} login type:{} type val:{}",username,loginType.name(),val);
        UserInfo userInfo = null;
        try {
            switch (loginType.findType()) {
                case FindById:
                    userInfo = userInfoRepo.findOne(Long.valueOf(val));
                    break;
                case FindByTelephone:
                    userInfo = userInfoRepo.findByTelephone(val);
                    break;
            }
        }catch (Exception e){
            log.error("find user error, username:{}, type:{}",username,loginType);
        }
        return userInfo;
    }

    public static void main(String[] args) {

    }

} 