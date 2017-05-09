package com.hg.dbcanal.ini;

import com.hg.db.mysql.SimpleCanalClientTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 系统初始化执行
 */
@Slf4j
@Component
public class SysInitBean implements InitializingBean {


    public void afterPropertiesSet() throws Exception {
        SimpleCanalClientTest.main(null);
    }
}
