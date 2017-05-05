package com.hg.hystrix;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.*;
import com.netflix.hystrix.util.HystrixRollingNumberEvent;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2016/1/21.
 */
//重载HystrixCommand 的getFallback方法实现逻辑
public class HelloWorldCommandTime extends HystrixCommand<String> {
    private final String name;
    public HelloWorldCommandTime(String name) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                /* 配置依赖超时时间,500毫秒*/
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withRequestLogEnabled(true)
                        .withExecutionIsolationThreadTimeoutInMilliseconds(5000)));
        this.name = name;
    }
    @Override
    protected String getFallback() {
        return Thread.currentThread().getName() + "exeucute Falled";
    }

    @Override
    protected String run() throws Exception {
        //sleep 1 秒,调用会超时
        TimeUnit.MILLISECONDS.sleep(100);
        return "Hello " + name +" thread:" + Thread.currentThread().getName();
    }
    public static void main(String[] args) throws Exception{
        for(int i=0;i<10; i++){
            HelloWorldCommandTime command = new HelloWorldCommandTime("test-Fallback");
            String result = command.execute();
//            System.out.println(JSON.toJSONString(getHystrixMetrics()) );
            System.out.println(result);
        }

    }


}