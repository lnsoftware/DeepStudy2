package com.bfa.sbgl.app.api.test.controller;

/**
 * Created by wangqinghui on 2016/2/4.
 */


import com.alibaba.fastjson.JSON;
import com.bfa.sbgl.app.api.test.module.*;
import com.bfa.sbgl.app.api.test.service.ITestUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/test/user")
public class TestUserInfoController {

    private CounterService counterService;

    @Autowired
    private ITestUserService userService;


    @ApiOperation(value = "获取用户信息", nickname = "GetUserInfo", response = GetUserInfoS2c.class)
    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Meta> update( @ApiParam(value = "getUserInfoC2s", required = true, name = "get user req" , defaultValue = "dfdf") @RequestBody GetUserInfoC2s getUserInfoC2s) {
        log.info("get user info, req:{}", JSON.toJSONString(getUserInfoC2s));
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "更新用户信息", nickname = "修改用户信息", response = UpdateUserS2c.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Meta> update(@RequestBody UpdateUserC2s userId) {
        return ResponseEntity.ok(null);
    }


}