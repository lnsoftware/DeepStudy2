package com.zhiyin.oauth2.oauthserver.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hg on 2016/6/23.
 */
@Slf4j
@RestController
@RequestMapping("/")
public class TokenCon {

    @Autowired
    private DefaultTokenServices tokenServices;

    @RequestMapping("/token/re/{token}")
    public ResponseEntity<String> hello(@PathVariable("token") String token) {

        log.info("token:" + token);

        boolean ret = tokenServices.revokeToken(token);

        return ResponseEntity.ok("token:"+token + "  "+ret);
    }

}
