package com.zhiyin.mysql.lock.domain;

import lombok.Data;

@Data
public class OptimisticUser {

    private Long id;
    private String value;
    private Integer version;

}
