package com.zhiyin.ourchat.consumer.controller;



import com.zhiyin.ranker.api.client.ContentListenClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hg on 2016/8/5.
 */

@Slf4j
@RestController
@RequestMapping("/api/")
public class RankeApiController {

    @Autowired
    private ContentListenClient contentListenClient;

    @RequestMapping(method = RequestMethod.GET, path = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String greeting() {
        log.info("call http api.");
        return contentListenClient.hello("hh");
    }
}
