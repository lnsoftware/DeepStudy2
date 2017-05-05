package com.zhiyin.queue.core.type;

import lombok.Getter;

/**
 * Created by wangqinghui on 2016/3/9.
 */
@Getter
public enum ProducerType {

    HGTEST(1,"HG_TEST"),

    DBOP(1,"PID_DB_OP_TEST");


    // 定义私有变量
    private int id ;
    private String name;

    // 构造函数，枚举类型只能为私有
    private ProducerType( int id, String name) {
        this.id = id;
        this.name = name;
    }

}
