package com.zhiyin.queue.core.factory;

import com.alibaba.fastjson.JSON;
import com.zhiyin.event.core.body.binlog.BinlogEventBody;
import com.zhiyin.event.core.body.binlog.BinlogOpType;
import com.zhiyin.queue.core.event.AliQueueEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by wangqinghui on 2016/3/15.
 */
@Slf4j
public class QueueEventGenFactoryTest {

    @Test
    public void testGenTest() throws Exception {

//        EventEntity event = BinlogEventFactory.contentAdd(1L);
//
//        AliQueueEvent queue = AliQueueEventFactory.gen("topic", event);
//
//        log.info(JSON.toJSONString(queue));

        BinlogEventBody body = new BinlogEventBody();
        body.setDbName("zhiyin");
        body.setTableName("zhiyin_content_basic_content");
        body.setOpType(BinlogOpType.UPDATE);
        body.setDataId(1L);

        AliQueueEvent queueEvent = AliQueueEventFactory.genDbOpEvent( body );

        log.info(JSON.toJSONString(queueEvent));


    }
}