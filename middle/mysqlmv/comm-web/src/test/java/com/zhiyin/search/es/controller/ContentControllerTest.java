package com.zhiyin.search.es.controller;

import com.zhiyin.search.es.module.ContentCommonC2s;
import org.junit.Test;

/**
 * Created by hg on 2016/4/3.
 */
public class ContentControllerTest extends ControllerTestBase {

    @Test
    public void testAddOrUpdateById() throws Exception {

        ContentCommonC2s c2s = new ContentCommonC2s();
        c2s.setId(1001L);

        String url = "/contents/addOrUpdateById";

        this.sendPost(url, c2s);

    }
}