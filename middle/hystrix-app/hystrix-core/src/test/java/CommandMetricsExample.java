import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolMetrics;

/**
 * 获取Hystrix的metric数据
 * Created by hg on 2017/4/21.
 */
public class CommandMetricsExample {

    public static String commandKey = "helloCommand";

    public static void main(String[] args) throws Exception {
        HelloCommand command = new HelloCommand();
        command.execute();

        HystrixThreadPoolMetrics poolMetrics = HystrixThreadPoolMetrics.getInstance(HystrixThreadPoolKey
            .Factory.asKey("HelloWorldGroup"));
        HystrixCommandMetrics commandMetrics = HystrixCommandMetrics.getInstance(HystrixCommandKey.Factory.asKey(commandKey));

        System.out.println(commandMetrics.getExecutionTimeMean());
        System.out.println(poolMetrics.getCurrentCorePoolSize());
        System.out.println(poolMetrics.getThreadPool().getPoolSize());
    }

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
}


