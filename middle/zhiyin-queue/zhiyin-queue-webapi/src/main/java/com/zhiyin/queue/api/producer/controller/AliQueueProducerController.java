package com.zhiyin.queue.api.producer.controller;


import com.alibaba.fastjson.JSON;
import com.zhiyin.queue.config.SystemConfig;
import com.zhiyin.queue.core.event.AliQueueEvent;
import com.zhiyin.queue.core.type.TopicType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ali Queue服务接口
 */
@Slf4j
@RestController
@RequestMapping("/aliq")
@Api(value = "AliQueue", description = "ali队列接口")
public class AliQueueProducerController {

//    @RequestMapping(method = RequestMethod.GET, path = "/test" )
//    public String test() {
//        String str =  UUID.randomUUID().toString();
//        try {
//
//            EventEntity event = BinlogEventFactory.contentAdd(1L);
//            AliQueueEvent msg = AliQueueEventFactory.gen(TopicType.HGTEST.getName(), event);
//
//            SystemConfig.ProduceAliTestQueueEvent.put(msg);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return str;
//
//    }

    /**
     * Ali事件处理
     * @param event
     * @return
     */
    @ApiOperation( value = "生产者事件", notes = "生产者事件接口", response = String.class )
    @RequestMapping(method = RequestMethod.POST, value = "/put/event", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String put(@RequestBody AliQueueEvent event) {

        log.info("rec event:{}",JSON.toJSONString(event));

        if( TopicType.HGTEST.getName().equals(event.getTopic()) ){
            try {
                log.debug("rec event:{}", JSON.toJSONString(event));
                SystemConfig.ProduceAliTestQueueEvent.put(event);
            } catch (InterruptedException e) {
                log.error("put msg error. ");
            }
        }

        log.info(event.getTopic());
        if( TopicType.DBOP.getName().equals(event.getTopic()) ){
            try {
                log.debug("rec event:{}", JSON.toJSONString(event));
                SystemConfig.ProduceAliDbopQueueEvent.put(event);
                log.info("put {} event succ.",TopicType.DBOP.getName());
            } catch (InterruptedException e) {
                log.error("put msg error. ");
            }
        }



        return "success";
    }
}
