import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2016/1/21.
 */
//重载HystrixCommand 的getFallback方法实现逻辑
public class HelloWorldCommand extends HystrixCommand<String>{

    public static String RetSucc = "Ok";
    public static String RetFail = "fail";
    private final String name;
    private Integer sleepMilliSecond;
    public HelloWorldCommand(String commandKey, Integer sleepMilliSecond) {
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
//        if(true){
//            throw new Exception("ss");
//        }
        //sleep 1 秒,调用会超时
        TimeUnit.MILLISECONDS.sleep( sleepMilliSecond );
        return RetSucc;
    }

    public static void main(String[] args) throws Exception{
        HelloWorldCommand  command = new HelloWorldCommand ("test-Fallback",1000);
            String result = command.execute();
        System.out.println(result);
    }

}