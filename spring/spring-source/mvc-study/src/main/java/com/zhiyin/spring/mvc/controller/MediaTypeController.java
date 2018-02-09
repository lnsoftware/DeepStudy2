package com.zhiyin.spring.mvc.controller;

import com.zhiyin.spring.mvc.model.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangqinghui on 2017/12/21.
 */
@Slf4j
@RestController
public class MediaTypeController {

//    @RequestMapping(value = "/mediaTest", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/mediaTest", method = RequestMethod.POST)
    public User getUser(@RequestBody User user) {
        log.info("req:"+user);
        return user;
    }

    @RequestMapping(value = "/jackGet", method = RequestMethod.GET)
    public User getUser(@RequestParam(value = "userId") Long userId
                                    ) {


        return new User();
    }


}
