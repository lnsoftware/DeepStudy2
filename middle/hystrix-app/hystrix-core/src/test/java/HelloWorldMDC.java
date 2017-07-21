import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by hg on 2016/1/21.
 */
public class HelloWorldMDC extends HystrixCommand<String> {

    public static String RetSucc = "Ok";
    public static String RetFail = "fail";
    private final String name;
    private Integer sleepMilliSecond;

    public HelloWorldMDC(String commandKey, Integer sleepMilliSecond) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup")).andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
            .andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter()
                    .withRequestLogEnabled(true)
            ));
        this.name = commandKey;
        this.sleepMilliSecond = sleepMilliSecond;
    }

    public static void main(String[] args) throws Exception {

//        MDC.
        HelloWorldCommandTime command = new HelloWorldCommandTime("test-Fallback", 1000);
        String result = command.execute();
        System.out.println(result);
//        HystrixCommandGroupKey.Factory.asKey("").
    }

    @Override
    protected String getFallback() {
        return RetFail;
    }

    @Override
    protected String run() throws Exception {

        return RetSucc;
    }
}