package com.zhiyin.hystrix.module.annodemo.httpservice;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhiyin.hystrix.module.annodemo.entity.Message;
import com.zhiyin.hystrix.module.annodemo.entity.MessageAcknowledgement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by hg on 2016/1/21.
 */

@Slf4j
@Service
public class MessageHandlerServiceImpl implements MessageHandlerService {

//    public MessageAcknowledgement handleMessage(Message message) {
//        log.info("About to Acknowledge");
//        try {
//            Thread.sleep(message.getDelayBy());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return new MessageAcknowledgement(message.getId(), message.getPayload(), UUID.randomUUID().toString());
//    }


    @HystrixCommand(groupKey = "Annotation", fallbackMethod = "defaultMessage", commandKey = "RemoteMessageAnnotationClient")
    public MessageAcknowledgement handleMessage(Message message) {
        log.info("About to Acknowledge");
        try {
            Thread.sleep(message.getDelayBy());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new MessageAcknowledgement(message.getId(), message.getPayload(), UUID.randomUUID().toString());
    }

    public MessageAcknowledgement defaultMessage(Message message) {
        return new MessageAcknowledgement("-1", message.getPayload(), "Fallback Payload");
    }


}
