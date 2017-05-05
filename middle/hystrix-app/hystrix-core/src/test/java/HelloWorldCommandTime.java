import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.*;
import com.netflix.hystrix.util.HystrixRollingNumberEvent;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2016/1/21.
 */
//重载HystrixCommand 的getFallback方法实现逻辑
public class HelloWorldCommandTime extends HystrixCommand<String>{

    public static String RetSucc = "Ok";
    public static String RetFail = "fail";
    private final String name;
    private Integer sleepMilliSecond;
    public HelloWorldCommandTime(String commandKey,Integer sleepMilliSecond) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup")).andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withRequestLogEnabled(true)
                ));
        this.name = commandKey;
        this.sleepMilliSecond = sleepMilliSecond;
    }
    @Override
    protected String getFallback() {
        return RetFail;
    }

    @Override
    protected String run() throws Exception {
        if(true){
            throw new Exception("ss");
        }
        //sleep 1 秒,调用会超时
        TimeUnit.MILLISECONDS.sleep( sleepMilliSecond );
        return RetSucc;
    }

    public static void main(String[] args) throws Exception{
        HelloWorldCommandTime command = new HelloWorldCommandTime("test-Fallback",1000);
            String result = command.execute();
        System.out.println(result);
    }
//    public static void main(String[] args) throws Exception{
//
//
//
//
//        ConfigurationManager.getConfigInstance().setProperty(
//                "hystrix.command.TestTimeoutKey.execution.isolation.thread.timeoutInMilliseconds",
//                5000);
//
//
//
//
//        ConfigurationManager.getConfigInstance().setProperty(
//                "hystrix.command.TestTimeoutKey.execution.isolation.thread.timeoutInMilliseconds",
//                5000);
//
//        for(int i=0;i<10; i++){
//            HelloWorldCommandTime command = new HelloWorldCommandTime("test-Fallback");
//            String result = command.execute();
//            System.out.println(result);
//        }
//
//        System.out.println();
//        System.out.println();
//
//        ConfigurationManager.getConfigInstance().setProperty(
//                "hystrix.command.TestTimeoutKey.execution.isolation.thread.timeoutInMilliseconds",
//                5000);
//
//        for(int i=0;i<10; i++){
//            HelloWorldCommandTime command = new HelloWorldCommandTime("test-Fallback");
//            String result = command.execute();
//            System.out.println(result);
//        }
//
//    }


}