package com.zhiyin.mpush.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by wangqinghui on 2016/5/18.
 */
@Getter
@Setter
public class TestBasicData extends BaseEntity {
    private Long id;


    private String title;
    private Long testTime;

    private Date createTime;

    private Date updateTime;

    private Integer delStatus;

}