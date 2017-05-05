package com.zhiyin.queue.api.producer.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

/**
 * Created by hg on 2016/4/2.
 */
@Slf4j
@Controller
public class TestOkController {

    @ApiOperation(value = "更新用户信息", nickname = "修改用户信息", response = UpdateUserS2c.class)
    @RequestMapping(value = "/testok", method = RequestMethod.GET)
    public ResponseEntity<String> update() {
        log.info("test get");
        return ResponseEntity.ok("hello");
    }

}

@Getter
@Setter
@ToString
class UpdateUserC2s implements Serializable {

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "用户编号", required = true)
    private long id;

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "用户名", required = true)
    private String name;
}



@Getter
@Setter
@ToString
class UpdateUserS2c implements Serializable {

    private static final long serialVersionUID = -5267482584689902883L;

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "用户编号", required = true)
    private long id;

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "用户名", required = true)
    private String name;

}

