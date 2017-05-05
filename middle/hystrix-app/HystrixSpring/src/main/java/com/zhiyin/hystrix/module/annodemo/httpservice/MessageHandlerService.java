package com.zhiyin.hystrix.module.annodemo.httpservice;


import com.zhiyin.hystrix.module.annodemo.entity.Message;
import com.zhiyin.hystrix.module.annodemo.entity.MessageAcknowledgement;

/**
 * Created by hg on 2016/1/21.
 */
public interface MessageHandlerService {
    public MessageAcknowledgement handleMessage(Message message) ;
    }
