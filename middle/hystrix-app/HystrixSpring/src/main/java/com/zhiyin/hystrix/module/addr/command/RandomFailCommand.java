package com.zhiyin.hystrix.module.addr.command;

import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;

/**
 * Created by hg on 2016/4/13.
 */
@Slf4j
public class RandomFailCommand extends HystrixCommand<String> {

    public RandomFailCommand() {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("hystrix.command.http.group"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("hystrix.command.http"))
//                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("hystrix.command.http"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withCircuitBreakerRequestVolumeThreshold(2)
                                        .withCircuitBreakerSleepWindowInMilliseconds(60 * 1000)));

    }

    @Override
    protected String run() throws Exception {

        boolean fail = RandomUtils.nextBoolean();
        if(fail) {
            throw new RuntimeException();
        }

        return Thread.currentThread().getName() + "succ";
    }

    @Override
    protected String getFallback() {
        return Thread.currentThread().getName() + "exeucute Falled";
    }

    public static void main(String[] args) throws Exception{
        while(true){
            RandomFailCommand command = new RandomFailCommand();
            String result = command.execute();
            System.out.println(result);
        }

    }

}

