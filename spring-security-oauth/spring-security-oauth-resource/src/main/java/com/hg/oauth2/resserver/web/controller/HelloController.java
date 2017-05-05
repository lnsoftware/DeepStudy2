package com.hg.oauth2.resserver.web.controller;

import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * just for test server.
 * Created by hg on 2016/6/15.
 */
@Slf4j
@RestController
@RequestMapping("/")
public class HelloController {

    @RequestMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam(value = "name", required = false) String name) {
        name = Optional.fromNullable(name).or("default");
        return ResponseEntity.ok("hello "+name + ", I'm rs.");
    }

}
