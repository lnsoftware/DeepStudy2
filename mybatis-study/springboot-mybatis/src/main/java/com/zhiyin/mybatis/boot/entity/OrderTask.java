package com.zhiyin.mybatis.boot.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by wangqinghui on 2017/11/2.
 */
@Data
public class OrderTask {
    private Long id;
    private Integer status;
    private Date created;
}
