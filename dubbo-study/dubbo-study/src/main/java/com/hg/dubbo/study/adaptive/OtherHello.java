package com.hg.dubbo.study.adaptive;

import com.alibaba.dubbo.common.extension.SPI;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hg on 2017/4/22.
 */
@Slf4j
@SPI
public class OtherHello implements Hello {

    @Override
    public void self() {
        log.info("I'm other.");
    }

    @Override
    public void say(String word) {
        log.info("other say:"+word);
    }
}
