package com.hg.oauth2.resserver.web.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hg on 2016/7/9.
 */
@Slf4j
@RestController

public class UT {


    @RequestMapping("/uc")
    public ResponseEntity<String> hello(@RequestParam(value = "name", required = false) String name) {


        log.info(JSON.toJSONString(oauthUserInfo()));
        name = Optional.fromNullable(name).or("default");
        return ResponseEntity.ok("hello "+name + ", I'm " + oauthUserInfo() +".");
    }


    public Object oauthUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof String
                    && ((String) principal).equals("AnonymousUser")) {
                log.warn("access user info is null, user token is:{}", JSON.toJSONString(principal));
            } else {
                // TODO 不要使用userInfoService获取用户信息
//                OauthUserDetails userDetails = null;
//                try {
//                    userDetails = (OauthUserDetails) principal;
//                }catch (Exception e){
//                    logger.error("conv oauth user detail error,",e);
//                }
//
//                OauthUserInfo tmp = userDetails.getUser();
//
//                haveLogin = true;
                return principal;
            }
        } else {
            log.warn("authentication info is null");
        }
        return null;

        // UserInfo tmp = new UserInfo();
        // tmp.setId(4216609961720602624L);
        //
        // return tmp;

    }


}
