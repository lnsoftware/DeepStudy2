package com.hg.oauth2.resserver.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by hg on 2016/6/23.
 */
@RestController
public class AppController {

    @RequestMapping("/app/hello")
    @PreAuthorize("hasRole('ROLE_APP') and #oauth2.hasScope('read')")
    public String adminResource(Principal user) {
        return "{\"id\":\"" + user.getName() + "\",\"content\":\"Hello World\"}";
    }
}
