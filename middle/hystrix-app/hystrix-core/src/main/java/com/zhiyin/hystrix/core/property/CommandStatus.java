package com.zhiyin.hystrix.core.property;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import lombok.Data;

@Data
public class CommandStatus {
    private boolean isResponseShortCircuited;
    private boolean isCircuitBreakerOpen;
    private boolean isResponseRejected;
    private boolean isResponseTimedOut;
    private int executionTimeInMilliseconds;

    private int executionTimeoutInMilliseconds;
    private Throwable failedExecutionException;
    private int corePoolSize;

    @Override
    public String toString() {

        ValueFilter valueFilter = new ValueFilter() {
            @Override
            public Object process(Object o, String propertyName, Object propertyValue) {
                if ("failedExecutionException".equals(propertyName)) {
                    if (propertyValue == null) {
                        return "";
                    }
                    return ((Throwable) propertyValue).getMessage();
                }
                return propertyValue;
            }
        };

        return JSON.toJSONString(this, valueFilter);

    }

}