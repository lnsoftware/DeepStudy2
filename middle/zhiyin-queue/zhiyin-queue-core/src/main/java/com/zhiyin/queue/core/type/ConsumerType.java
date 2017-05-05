package com.zhiyin.queue.core.type;

import lombok.Getter;

/**
 * 消费队列类型
 * Created by hg on 2016/3/9.
 */
@Getter
public enum ConsumerType {

    HGTEST(1,"CID_HG_TEST",TopicType.HGTEST.getName()),
    ESINDEXUPDATE(1,"CID_ES_INDEX_UPDATE_TEST",TopicType.DBOP.getName());


    // 定义私有变量
    private int id ;
    private String name;
    private String topic;

    // 构造函数，枚举类型只能为私有
    private ConsumerType( int id,String name,String topic) {
        this.id = id;
        this.name = name;
        this.topic = topic;
    }

}
