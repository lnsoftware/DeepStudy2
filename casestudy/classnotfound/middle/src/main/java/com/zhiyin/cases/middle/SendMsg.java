package com.zhiyin.cases.middle;

import com.zhiyin.cases.Producer;

/**
 * Created by hg on 2018/2/8.
 */
public class SendMsg {

    public static void send(String str){
        Producer producer = new Producer();
        producer.send(str);
    }
}
