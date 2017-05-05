package com.zhiyin.queue.ini;

import com.aliyun.openservices.ons.api.*;
import com.zhiyin.event.core.EventEntity;
import com.zhiyin.event.core.body.binlog.BinlogEventBody;
import com.zhiyin.queue.config.HttpUrlConfig;
import com.zhiyin.queue.config.QueueConfig;
import com.zhiyin.queue.config.SystemConfig;
import com.zhiyin.queue.core.event.AliQueueEvent;
import com.zhiyin.queue.core.type.ConsumerType;
import com.zhiyin.queue.task.ComsumeAliDbopQueueTask;
import com.zhiyin.queue.task.ComsumeAliTestQueueTask;
import com.zhiyin.queue.task.ProduceAliDbopQueueTask;
import com.zhiyin.queue.task.ProduceAliTestQueueTask;
import com.zhiyin.utils.bean.BeanMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 系统初始化执行
 */
@Slf4j
@Component
public class SysInitBean implements InitializingBean {

    @Autowired
    private HttpUrlConfig httpUrlConfig;

    public void afterPropertiesSet() throws Exception {

        startTestQueueConsumer();
        startDbopQueueConsumer();

        Thread thread = new ProduceAliTestQueueTask("t1");
        thread.start();
        log.info("处理生成者队列启动成功");

        Thread thread2 = new ComsumeAliTestQueueTask("t2");
        thread2.start();
        log.info("消费者线程启动。");


        Thread dbopThread = new ProduceAliDbopQueueTask("dbopThread");
        dbopThread.start();
        log.info("处理生成者队列启动成功");

        Thread dbopThread2 = new ComsumeAliDbopQueueTask("ComsumeAliDbopQueueTask");
        log.info("ConsumeBinlogEvent");
        dbopThread2.start();

    }

    /**
     * 测试队列监听，接收消息，并放入本地队列用于接收
     */
    public void startTestQueueConsumer() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId, ConsumerType.HGTEST.getName());
        properties.put(PropertyKeyConst.AccessKey, QueueConfig.acckey);
        properties.put(PropertyKeyConst.SecretKey, QueueConfig.seckey);
        Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe(ConsumerType.HGTEST.getTopic(), "*", new MessageListener() {
            public Action consume(Message message, ConsumeContext context) {

                AliQueueEvent event = BeanMapper.map(message, AliQueueEvent.class);

                try {
                    SystemConfig.ConsumeAliTestQueueEvent.put(event);
                } catch (InterruptedException e) {
                    log.error("get queue error.", e);
                }
                return Action.CommitMessage;
            }
        });
        consumer.start();
        log.info("监听Ali Consumer Test Queue起动。");
    }

    public void startDbopQueueConsumer() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId, ConsumerType.ESINDEXUPDATE.getName());
        properties.put(PropertyKeyConst.AccessKey, QueueConfig.acckey);
        properties.put(PropertyKeyConst.SecretKey, QueueConfig.seckey);
        Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe(ConsumerType.ESINDEXUPDATE.getTopic(), "*", new MessageListener() {
            public Action consume(Message message, ConsumeContext context) {

                log.info("dbop comsumer rec msg:{}",message);
                String eventStr = new String(message.getBody());
                EventEntity event = EventEntity.deserialize(eventStr);
                BinlogEventBody body = (BinlogEventBody) event.getBody();

                if( body == null ){
                    log.error("binglog even body is null.");
                    return Action.CommitMessage;
                }
                try {
                    SystemConfig.ConsumeBinlogEvent.put(body);
                } catch (InterruptedException e) {
                    log.error("get queue error.", e);
                }
                return Action.CommitMessage;
            }
        });
        consumer.start();
        log.info("监听Ali Consumer Test Queue起动。");
    }

}
