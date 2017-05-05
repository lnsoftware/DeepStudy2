package com.zhiyin.queue.core.factory;

import com.zhiyin.event.core.EventEntity;
import com.zhiyin.event.core.body.binlog.BinlogEventBody;
import com.zhiyin.event.core.factory.BinlogEventFactory;
import com.zhiyin.queue.core.event.AliQueueEvent;
import com.zhiyin.queue.core.type.TopicType;
import com.zhiyin.utils.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by wangqinghui on 2016/3/15.
 */
@Slf4j
public class AliQueueEventFactory {

    /**
     * Ali队列
     * @return
     */
    public static AliQueueEvent gen(String topic, EventEntity event){

        AliQueueEvent msg = new AliQueueEvent();

        msg.setEvent(event);
        msg.setTag("test");

        msg.setTopic( topic );
        return msg;
    }


    public static AliQueueEvent genTestTopic( EventEntity event){

        AliQueueEvent msg = new AliQueueEvent();
        msg.setEvent(event);
        msg.setTag("test");

        msg.setTopic( TopicType.HGTEST.getName() );
        return msg;
    }


    /**
     * 数据库操作队列事件
     * @param event
     * @return
     */
    @Deprecated
    public static AliQueueEvent genDbOpEvent( EventEntity event){

        AliQueueEvent msg = new AliQueueEvent();

        // 调用原生的序列化方式
        msg.setEvent(event);
        msg.setTag("dbop");


        msg.setTopic( TopicType.DBOP.getName() );


        return msg;
    }


    public static AliQueueEvent genDbOpEvent( BinlogEventBody body){

        AliQueueEvent msg = new AliQueueEvent();
        EventEntity event = BinlogEventFactory.binlog(body);

        // 调用原生的序列化方式
        msg.setEvent(event);
        msg.setTag("dbop");

        msg.setTopic( TopicType.DBOP.getName() );

        log.info("ali queue event body:{}",msg.getEventStr());

        return msg;
    }


    /**
     * 解析数据库操作事件
     * @param queue
     * @return
     */
//    @Deprecated
    public static EventEntity reDbopEvent(AliQueueEvent queue){

        if(  TopicType.DBOP.getName().equals( queue.getTopic() ) ){
            String str = queue.getEventStr();
            log.info("rec event:" + str);
            EventEntity event = EventEntity.deserialize(str);
            if(event == null){
                log.error("get ali event is null, {}", JSONUtil.toJson(queue));
                throw new RuntimeException("event is null.");
            }

            return event;
        }

        log.error("reDbopEvent error.");
        return null;
    }


}
