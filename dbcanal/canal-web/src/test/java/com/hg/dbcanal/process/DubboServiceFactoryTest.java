package com.hg.dbcanal.process;

import com.alibaba.fastjson.JSON;
import com.zhiyin.dbs.factory.DubboServiceFactory;
import com.zhiyin.dbs.module.address.entity.CustomAddress;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hg on 2016/6/5.
 */
@Slf4j
public class DubboServiceFactoryTest {

    public static void main(String[] args){
        CustomAddress addr = DubboServiceFactory.customAddressService().selectById(101L);
        log.info(JSON.toJSONString(addr));
    }
}
