package com.hg.dbcanal.process;

import com.zhiyin.event.core.body.binlog.BinlogEventBody;

import static org.junit.Assert.*;

/**
 * Created by hg on 2016/5/13.
 */
public class BinlogProcessorTest {

    @org.junit.Test
    public void testProcess() throws Exception {

        BinlogEventBody tmp = new BinlogEventBody();
        tmp.setDbName("zhiyin");
        tmp.setTableName("zhiyin_addr_custom_address_location");
        tmp.setDataId(1001L);
        BinlogProcessor.process(tmp);

    }
}