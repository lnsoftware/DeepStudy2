package com.zhiyin.queue.task;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.*;
import com.google.common.base.Strings;
import com.zhiyin.queue.config.QueueConfig;
import com.zhiyin.queue.config.SystemConfig;
import com.zhiyin.queue.core.event.AliQueueEvent;
import com.zhiyin.queue.core.type.ProducerType;
import com.zhiyin.utils.bean.BeanMapper;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 用于向AliQueue发送消息,生成mysqlbinlog事件
 * Created by wangqinghui on 2016/3/9.
 */
@Slf4j
public class ProduceAliDbopQueueTask extends Thread {

    public ProduceAliDbopQueueTask(String name) {
        super(name);
    }

    @Override
    public void run() {

        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId, ProducerType.DBOP.getName() );
        properties.put(PropertyKeyConst.AccessKey, QueueConfig.acckey);
        properties.put(PropertyKeyConst.SecretKey, QueueConfig.seckey);
        Producer producer = ONSFactory.createProducer(properties);

        //在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
        producer.start();

        while ( true ) {

            try {
                AliQueueEvent event = SystemConfig.ProduceAliDbopQueueEvent.take();

                Message msg = BeanMapper.map(event, Message.class);
                String eventStr = event.getEventStr();
                if(Strings.isNullOrEmpty(eventStr)){
                    log.error("send event str is null.");
                    continue;
                }else{
                    msg.setBody(event.getEventStr().getBytes());
                }
                msg.setKey(DateTime.now().getMillis() + "");

                //发送消息，只要不抛异常就是成功
                SendResult sendResult = producer.send(msg);
                log.info("send to ali server,send msg id:{}, event:{}",sendResult.getMessageId(),JSON.toJSONString(msg));
            } catch (InterruptedException e) {
                log.error("send message error.",e);
            }

            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                log.error("sleep thread error.",e);
            }
        }

    }


}
