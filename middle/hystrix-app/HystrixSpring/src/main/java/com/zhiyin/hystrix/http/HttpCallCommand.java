package com.zhiyin.hystrix.http;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.Feign;
import feign.Logger;
import feign.Param;
import feign.RequestLine;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2016/4/13.
 */
public class HttpCallCommand extends HystrixCommand<String> {

    private final String name;
    public HttpCallCommand(String name) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                /* 配置依赖超时时间,500毫秒*/
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(5000)));
        this.name = name;
    }
    @Override
    protected String getFallback() {
        return Thread.currentThread().getName() + "exeucute Falled";
    }

    @Override
    protected String run() throws Exception {

        return "";

//        Decoder decoder = new GsonDecoder();
//        GitHub github = Feign.builder()
//                .decoder(decoder)
//                .errorDecoder(new GitHubErrorDecoder(decoder))
//                .logger(new Logger.ErrorLogger())
//                .logLevel(Logger.Level.BASIC)
//                .target(GitHub.class, "https://api.github.com");



    }
    public static void main(String[] args) throws Exception{
        for(int i=0;i<10; i++){
            HttpCallCommand command = new HttpCallCommand("test-Fallback");
            String result = command.execute();
            System.out.println(result);
        }

    }
}