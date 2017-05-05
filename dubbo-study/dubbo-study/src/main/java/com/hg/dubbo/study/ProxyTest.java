package com.hg.dubbo.study;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.ProxyFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hg on 2016/8/30.
 */
@Slf4j
public class ProxyTest {
    public static void main(String[] args) {
        ProxyFactory proxyFactory =
                ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();

//        proxyFactory.
    }
}
