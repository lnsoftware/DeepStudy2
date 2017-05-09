package com.zhiyin.canal.processor;

import com.zhiyin.canal.model.RowOpType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wangqinghui on 2016/12/12.
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcessorMapping {

    String databaseName() default NULL;
    String tableName() default NULL;

    int rowOpCode() default  1;

    RowOpType rowOpType() default  RowOpType.SELECT;

    public static final String NULL = "NULL_oZShvXLv0RMTBhe61cN7sssss";
}