package com.zhiyin.oauth.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.Map;

/**
 * Created by hg on 2017/2/23.
 */
@Slf4j
public class ZyAccessTokenConverter extends DefaultAccessTokenConverter {

    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {

        // 调用默认实现
        Map<String, Object> ret = (Map<String, Object>) super.convertAccessToken(token, authentication);

        Object principal = authentication.getUserAuthentication().getPrincipal();
        if (principal instanceof String) {

        }
        if (principal instanceof ZyUser) {
            ZyUser user = (ZyUser) principal;
            ret.put(UserIdUtil.UserIdKey, UserIdUtil.getIdStr(user.getUserId()));
            log.info("access token user id:{}", user.getUserId());
        }


        return ret;
    }

}