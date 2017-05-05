package com.zhiyin.search.es.controller;

import com.zhiyin.search.es.module.ContentCommonC2s;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ContentInfoTest extends ControllerTestBase {

    @Test
    public void get() throws Exception {
        String url = "/contents/get";
        ContentCommonC2s req = new ContentCommonC2s();
        long id = 1001;
        req.setId(id);
        req.setDocument("ssss");
        this.sendPost(url, req);
    }

    @Test
    public void add() throws Exception {

        String url = "/contents/add";
        log.info("start test " + url);

        ContentCommonC2s req = new ContentCommonC2s();
        long id = (long) (Math.random() * 10000);
        req.setId(id);
        req.setDocument("ssss");

        this.sendPost(url, req);
    }

}
