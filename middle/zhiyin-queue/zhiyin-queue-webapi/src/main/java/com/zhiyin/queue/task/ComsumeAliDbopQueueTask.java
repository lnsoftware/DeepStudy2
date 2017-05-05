package com.zhiyin.queue.task;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zhiyin.event.core.body.binlog.BinlogEventBody;
import com.zhiyin.event.core.body.binlog.BinlogOpType;
import com.zhiyin.http.factory.HttpRequestFactory;
import com.zhiyin.http.factory.HttpUrlFactory;
import com.zhiyin.queue.config.SystemConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 消费Ali队列消息
 * Created by wangqinghui on 2016/3/9.
 */
@Slf4j
public class ComsumeAliDbopQueueTask extends Thread {

    public static final String ServiceUrl = "";

    public ComsumeAliDbopQueueTask(String name) {
        super(name);
    }

    @Override
    public void run() {

        while (true) {

            try {
                BinlogEventBody body = SystemConfig.ConsumeBinlogEvent.take();

                log.info("ali db queue consumer build index, rec:{}",JSON.toJSONString(body));
                BinlogOpType opType = body.getOpType();

                if( !"zhiyin".equals(body.getDbName()) && !"zhiyin_content_basic_content".equals(body.getTableName() ) ){
                    log.warn("ingore not zhiyin.zhiyin_content_basic_content table");
                    continue;
                }
                // 构建索引
                if (BinlogOpType.UPDATE.equals(opType)) {
                    Long id = body.getDataId();
                    Map<String, Long> val = Maps.newHashMap();
                    val.put("id", id);
                    try {
                        HttpRequestFactory.post(HttpUrlFactory.get("contents.search.addOrUpdateByIdById"), val);
                        log.info("build search index, tb:{} id:{}",body.getTableName(),body.getDataId());
                    } catch (Exception e) {
                        log.error("build searcher index error", e);
                    }

                }else{
                    log.error("table optype is null.");
                }

                log.info("consume binlog event succ.");
                TimeUnit.SECONDS.sleep(10);

            } catch (InterruptedException e) {
                log.error("comsume ali dbop error.", e);
            }
        }
    }
}
