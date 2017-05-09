package com.zhiyin.canal.processor;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * Created by wangqinghui on 2016/12/12.
 */
@Data
public class ProcessorMethodWrapper {

    private Class<?> clazz;
    private Object classObj;
    private Method method;
    private String pattern;

}
