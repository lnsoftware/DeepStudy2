package com.blog.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by hg on 2017/9/25.
 */
public class HelloWorldHystrixTest {
    public static void main(String[] args) {
        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("helloworld"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("helloworld"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionTimeoutInMilliseconds(20000));
        HystrixCommand<String> command = new HystrixCommand<String>(setter) {
            @Override
            protected String run() throws Exception {
                return "hello";
            }
        };

        System.out.println(command.execute());
    }
}
