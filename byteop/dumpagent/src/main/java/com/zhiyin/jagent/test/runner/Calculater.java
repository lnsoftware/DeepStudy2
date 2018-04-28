package com.zhiyin.jagent.test.runner;

import org.slf4j.Logger;

/**
 * Created by wangqinghui on 2018/2/11.
 */
public class Calculater {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Calculater.class);

    public int add(int a, int b){
        int c = a+b;
        log.info("a:{} add b:{}, val:{}",a,b,c );
        return c;
    }
}
