package com.zhiyin.oauth2.oauthserver.web.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class SecurityController1 {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public SecurityController1() {
        super();
    }

    // API

    @RequestMapping(value = "/username1", method = RequestMethod.GET)
    public String currentUserName() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            final String currentPrincipalName = authentication.getName();
           log.info("Authentication: " + authentication);
            log.info("Principal: " + authentication.getPrincipal());
            return currentPrincipalName;
        }

        return null;
    }

}