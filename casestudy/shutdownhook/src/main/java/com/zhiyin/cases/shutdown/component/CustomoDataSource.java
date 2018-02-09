package com.zhiyin.cases.shutdown.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;

import javax.annotation.PreDestroy;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2018/2/7.
 */
@Slf4j
public class CustomoDataSource extends BasicDataSource {

    @PreDestroy
    public synchronized void close() throws SQLException {
        log.info("datasource close.");
        super.close();
        // 控制数据库连接关了之后，定时任务有机会执行
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            log.error("close datasource error.",e);
        }
    }
}
