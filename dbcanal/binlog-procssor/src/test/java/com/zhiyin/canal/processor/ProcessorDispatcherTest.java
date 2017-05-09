package com.zhiyin.canal.processor;

import com.zhiyin.canal.model.RowBinlog;
import com.zhiyin.canal.model.RowOpType;
import org.junit.Test;

/**
 * Created by wangqinghui on 2016/12/12.
 */
public class ProcessorDispatcherTest {

    @Test
    public void testDispatcher() throws Exception {

        ProcessorDispatcher.init("com.zhiyin.canal.processor");

        RowBinlog binlog = new RowBinlog();
        binlog.setDatabaseName("zhiyin");
        binlog.setTableName("userinfo");
        binlog.setRowOpType(RowOpType.DELETE);
        binlog.setRowId(1000L);

        ProcessorDispatcher.dispatcher(binlog);
    }
}