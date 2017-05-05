package com.zhiyin.oauth.core;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.Collection;
import java.util.Map;

/**
 * Created by hg on 2017/2/24.
 */
public class ZyUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    public Authentication extractAuthentication(Map<String, ?> map) {
        Authentication ret = super.extractAuthentication(map);
        if(ret == null){
            return null;
        }

        Long userId = UserIdUtil.getIdVal( (String) map.get(UserIdUtil.UserIdKey) );
        String userName = (String) map.get(USERNAME);
        // password没用，随便构造
        ZyUser zyUser = new ZyUser(userName,"",ret.getAuthorities(),userId);

        return new UsernamePasswordAuthenticationToken(zyUser, "N/A", ret.getAuthorities());
    }
}
