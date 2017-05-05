package com.zhiyin.queue.task;

import com.zhiyin.event.core.EventEntity;
import com.zhiyin.event.core.body.binlog.BinlogEventBody;
import com.zhiyin.event.core.factory.BinlogEventFactory;
import com.zhiyin.queue.config.SystemConfig;
import com.zhiyin.queue.core.event.AliQueueEvent;
import com.zhiyin.queue.core.factory.AliQueueEventFactory;
import org.junit.Test;

/**
 * Created by hg on 2016/3/24.
 */
public class ProduceAliDbopQueueTaskTest {

    @Test
    public void te() throws InterruptedException {
        BinlogEventBody eventInfo = new BinlogEventBody();
        eventInfo.setTableName("ss");
        EventEntity eventEntity = BinlogEventFactory.binglog(eventInfo);
        AliQueueEvent queueEvent = AliQueueEventFactory.genDbOpEvent( eventEntity );
//        AliQueueEvent event = SystemConfig.ProduceAliDbopQueueEvent.take();
        SystemConfig.ProduceAliDbopQueueEvent.put(queueEvent);

        ProduceAliDbopQueueTask dbopThread = new ProduceAliDbopQueueTask("dbopThread");
        dbopThread.run();
    }

}