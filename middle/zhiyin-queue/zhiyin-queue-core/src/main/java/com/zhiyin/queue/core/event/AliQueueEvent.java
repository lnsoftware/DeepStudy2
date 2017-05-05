package com.zhiyin.queue.core.event;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhiyin.event.core.EventEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * queueevent与 message对应
 * Created by wangqinghui on 2016/3/9.
 */
@Getter
@Setter
public class AliQueueEvent implements Serializable {

    private static final long serialVersionUID = -1385924226856188094L;
    private String topic;
    private String tag;
    private String key;

//    @Setter(AccessLevel.NONE)
    private String eventStr; // EventEntity的序列化值，通过setEvent赋值

    @JSONField(serialize=false)
    @JsonIgnore
    public void setEvent(EventEntity event){
        this.eventStr = event.serialize();
    }

    @JSONField(serialize=false)
    @JsonIgnore
    public EventEntity getEvent(){
        return EventEntity.deserialize(eventStr);
    }

}