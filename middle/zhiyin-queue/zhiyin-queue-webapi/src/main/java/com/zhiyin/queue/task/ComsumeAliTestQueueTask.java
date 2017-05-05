package com.zhiyin.queue.task;

import com.alibaba.fastjson.JSON;
import com.zhiyin.queue.config.SystemConfig;
import com.zhiyin.queue.core.event.AliQueueEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 消费AliTest队列消息
 * Created by wangqinghui on 2016/3/9.
 */
@Slf4j
public class ComsumeAliTestQueueTask extends Thread {

//    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    public static final String ServiceUrl = "";

    public ComsumeAliTestQueueTask(String name) {
        super(name);
    }

    @Override
    public void run() {

        while (true ) {

            try {
                AliQueueEvent msg = SystemConfig.ConsumeAliTestQueueEvent.take();
                String postStr = JSON.toJSONString(msg);
                log.info("receive and process message:{}", postStr);


//                OkHttpClient client = new OkHttpClient();
//
//
//                RequestBody body = RequestBody.create(MEDIA_TYPE, postStr);
//
//                Request request = new Request.Builder()
//                        .url(ServiceUrl)
//                        .post(body)
//                        .build();
//
//                Response response = client.newCall(request).execute();
//                if (response.isSuccessful()) {
//                    log.info("invoke http service error.");
//                }else{
//                    log.error("invoke http service succ.");
//                }
            }catch(Exception e){
                log.error("consume event error,",e);
            }

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
