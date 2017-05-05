package com.hg.oauth2.resserver.web.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.zhiyin.oauth.core.ZyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users/")
public class UserInfoController {
//    @Autowired
//    private TokenStore tokenStore;

//    @PreAuthorize("#oauth2.hasScope('read')")
//    @RequestMapping(method = RequestMethod.GET, value = "/users/extra")
//    public Map<String, Object> info(OAuth2Authentication auth) {
//        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
//        final OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
//        System.out.println(accessToken);
//        return accessToken.getAdditionalInformation();
//    }

    @RequestMapping("info4")
    public String user4( ) {

        Object prin = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info(JSON.toJSONString(prin));

        if( prin instanceof  ZyUser){
            log.info( "principal instanceof ZyUser");
        }
        User activeUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("user info: {}",activeUser.getUsername());
        return JSON.toJSONString(activeUser);
    }

    @RequestMapping("info")
    public String user(Principal principal) {
        log.info("princial info: {}",principal.getName());
        return JSON.toJSONString(principal);
    }

    @RequestMapping("info9")
    public Map<String, String> user2(Principal principal) {
        if (principal != null) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
            Authentication authentication = oAuth2Authentication.getUserAuthentication();
            Map<String, String> details = new LinkedHashMap<>();
            details = (Map<String, String>) authentication.getDetails();
//            logger.info("details = " + details);  // id, email, name, link etc.
            Map<String, String> map = new LinkedHashMap<>();
            map.put("email", details.get("email"));
            return map;
        }
        return null;
    }

    @RequestMapping("info6")
    public String user6(Principal principal) {
        log.info("princial info: {}",principal.getName());

        ZyUser activeUser = (ZyUser) ((Authentication) principal).getPrincipal();
        log.info(JSON.toJSONString(activeUser));
        return JSON.toJSONString(principal);
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping("info2")
    public String show(@AuthenticationPrincipal UserDetails user) {
        log.info("user detail info:{}.", user.getUsername() );
        return JSON.toJSONString(user);
    }

    @RequestMapping("info3")
    public String info3(@AuthenticationPrincipal UserDetails user) {
        return JSON.toJSONString(user);
    }


    @RequestMapping("info5")
    public String info5(@AuthenticationPrincipal ZyUser user) {
        return JSON.toJSONString(user);
    }

}
