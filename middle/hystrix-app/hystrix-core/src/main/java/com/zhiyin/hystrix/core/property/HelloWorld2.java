package com.zhiyin.hystrix.core.property;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import lombok.Data;
import org.aspectj.weaver.ast.Call;

import java.util.concurrent.TimeUnit;

/**
 * 重载HystrixCommand 的getFallback方法实现逻辑
 * Created by hg on 2016/1/21.
 */
public class HelloWorld2 extends HystrixCommand<CallResult>{

    public static CallResult RetSucc = new CallResult("ok");
    public static CallResult RetFail = new CallResult("fail");
    private final String name;
    private Integer sleepMilliSecond;
    public HelloWorld2(String commandKey, Integer sleepMilliSecond) {
        super(Setter.withGroupKey(HystrixCommandGroupKey
                .Factory.asKey("HelloWorldGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withRequestLogEnabled(true)
                ));
        this.name = commandKey;
        this.sleepMilliSecond = sleepMilliSecond;
    }

    @Override
    protected CallResult getFallback() {
        RetFail.setException(this.getExecutionException());
        return RetFail;
    }

    @Override
    protected CallResult run() throws Exception {

        if(true){
            throw new RuntimeException("ss");
        }

        TimeUnit.MILLISECONDS.sleep( sleepMilliSecond );
        return RetSucc;
    }

    public static void main(String[] args) throws Exception{
        HelloWorld2 command = new HelloWorld2("test-Fallback",2000);
        CallResult result = command.execute();
        System.out.println("result" + result);
        throw (Exception)result.getException();
    }

}



@Data
class CallResult {

    private String result;
    private Throwable exception;

    public CallResult(String result){
        this.result = result;
    }

}