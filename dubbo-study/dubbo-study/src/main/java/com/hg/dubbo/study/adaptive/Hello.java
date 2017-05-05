package com.hg.dubbo.study.adaptive;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * Created by hg on 2017/4/22.
 */
@SPI
public interface Hello {

//    @Adaptive
    public void self();

    @Adaptive
    public void say(String word);

//    @Adaptive("")
//    public void say(String word);

}
