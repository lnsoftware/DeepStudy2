package com.patterncat.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created by patterncat on 2016-01-23.
 */
@Component
@RefreshScope
public class DemoService {

    @Value("${msg:defaultMsg}")
    private String msg;

    /**
     * . 会被解析为/,即一个节点
     * 详见 ZookeeperTreeCachePropertySource
     */
    @Value("${first.second:tree}")
    private String tree;

    public String getMsg() {
        return "Hello " + this.msg + " " + this.tree;
    }
}
