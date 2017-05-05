package com.zhiyin.event.core.factory;


import com.alibaba.fastjson.JSON;
import com.zhiyin.event.core.EventProducerType;
import com.zhiyin.event.core.EventType;
import com.zhiyin.event.core.EventEntity;
import com.zhiyin.event.core.body.binlog.BinlogEventBody;
import com.zhiyin.event.core.body.content.ContentAddBody;

/**
 * mysql binlog event
 */
public class BinlogEventFactory {

	/**
	 * 创建mysql binlog事件
	 * @param body
	 * @return
     */
    @Deprecated
	public static EventEntity binglog(BinlogEventBody body) {

		EventEntity event = EventBuilderFactory.build(
				EventProducerType.MysqlBinlog, EventType.MysqlBinlog, body);

		return event;
	}

    public static EventEntity binlog(BinlogEventBody body) {

        EventEntity event = EventBuilderFactory.build(
                EventProducerType.MysqlBinlog, EventType.MysqlBinlog, body);

        return event;
    }

//    public static deBinlog(String eventStr){
//
//        JSON.parse(eventStr);
//
//
//    }



}
