import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

import java.util.concurrent.TimeUnit;

/**
 * 获取Hystrix的metric数据
 * Created by hg on 2017/4/21.
 */
public class CommandMetricsExample{

    public static String commandKey = "helloCommand";

    static class HelloCommand extends HystrixCommand<String> {
     public HelloCommand() {
         super(Setter.withGroupKey(HystrixCommandGroupKey
                 .Factory.asKey("HelloWorldGroup"))
                 .andCommandKey(HystrixCommandKey.Factory.asKey("hello"))
                 );
     }

     @Override
     protected String run() throws Exception {
         return "ok";
     }
 }

    public static void main(String[] args) throws Exception{
        HelloCommand command = new HelloCommand();
        command.execute();

        HystrixThreadPoolMetrics poolMetrics = HystrixThreadPoolMetrics.getInstance(HystrixThreadPoolKey
                .Factory.asKey("HelloWorldGroup"));
        HystrixCommandMetrics commandMetrics = HystrixCommandMetrics.getInstance(HystrixCommandKey.Factory.asKey( commandKey ));

        System.out.println(commandMetrics.getExecutionTimeMean());
        System.out.println(poolMetrics.getCurrentCorePoolSize());
        System.out.println(poolMetrics.getThreadPool().getPoolSize());
    }
}


