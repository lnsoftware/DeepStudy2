package com.zhiyin.event.core;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhiyin.event.core.body.BasicEventBody;
import com.zhiyin.event.core.body.EventBodyAdapter;
import com.zhiyin.event.core.body.ISerializable;
import com.zhiyin.event.core.body.binlog.BinlogEventBody;
import com.zhiyin.event.core.factory.BinlogEventFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

@Slf4j
@Getter
@Setter
public class EventEntity implements ISerializable {

    private String id;//事件id
    private String crateTime;//时间戳

    private BasicEventBody body;
    @Setter(AccessLevel.NONE)
    private String bodyStr; // 存储body的序列化值
    //	private EventType eventDetail = EventType.Undefine;
//	private EventProducerType producerType;//事件生产者 
    private String type = EventType.Undefine.getCode();

    private String producer;
    private String extra;//附加信息

    public EventEntity() {

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		this.dttm = sdf.format(new Date());
//		String s = UUID.randomUUID().toString();
//		this.id =(new StringBuilder()).append(s.substring(0, 8)).append(s.substring(9, 13)).append(s.substring(14, 18)).append(s.substring(19, 23)).append(s.substring(24)).toString();

    }

    private static Gson gson = (new GsonBuilder()).registerTypeAdapter(BasicEventBody.class, new EventBodyAdapter()).create();


    /**
     * 整体使用JSON序列化，body自定义序列化
     * @return
     */
    @Override
    public String serialize() {
        String bodyStr = gson.toJson(this.body, BasicEventBody.class);
        this.bodyStr = bodyStr;
        return JSON.toJSONString(this);
    }


    /**
     * 反序列化
     * @param eventStr
     * @return
     */
    public static EventEntity deserialize(String eventStr) {
        if (StringUtils.isEmpty(eventStr)) {
            return null;
        }

        EventEntity parseEvent = JSON.parseObject(eventStr, EventEntity.class);

        JSONObject jsonObj = JSON.parseObject(eventStr);
        String bodyStr = jsonObj.getString("bodyStr");
        BasicEventBody parseBodyObj = gson.fromJson(bodyStr, BasicEventBody.class);

        parseEvent.setBody(parseBodyObj);
        return parseEvent;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }



}
