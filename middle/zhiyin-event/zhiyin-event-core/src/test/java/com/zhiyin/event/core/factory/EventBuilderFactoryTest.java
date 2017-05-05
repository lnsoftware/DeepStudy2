package com.zhiyin.event.core.factory;

import com.alibaba.fastjson.JSON;
import com.zhiyin.event.core.EventProducerType;
import com.zhiyin.event.core.EventType;
import com.zhiyin.event.core.EventEntity;
import com.zhiyin.event.core.body.content.ContentAddBody;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by wangqinghui on 2016/3/15.
 */
@Slf4j
public class EventBuilderFactoryTest {

    @Test
    public void testBuildContentAddEvent() throws Exception {
        ContentAddBody tmp = new ContentAddBody();
        tmp.setId(100L);
        tmp.setTitle("Hello");
        EventEntity event = EventBuilderFactory.build(
                EventProducerType.AppManager, EventType.ContentAdd, tmp);

        log.info( JSON.toJSONString(tmp) );
    }
}