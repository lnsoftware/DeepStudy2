package com.zhiyin.event.core;

import com.alibaba.fastjson.JSON;
import com.zhiyin.event.core.body.binlog.BinlogEventBody;
import com.zhiyin.event.core.factory.BinlogEventFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wangqinghui on 2016/4/8.
 */
@Slf4j
public class EventEntityTest {

    @Test
    public void testSeri() {

        BinlogEventBody body = new BinlogEventBody();
        body.setDbName("zhiyin");

        EventEntity event = BinlogEventFactory.binlog(body);
        String eventStr = event.serialize();
        log.info(eventStr);

        EventEntity parseEvent = EventEntity.deserialize(eventStr);
        log.info( JSON.toJSONString(parseEvent.getBody()) );

        BinlogEventBody parseBody = (BinlogEventBody) parseEvent.getBody();

        Assert.assertTrue( parseBody.getDbName().equals("zhiyin"));
    }

}