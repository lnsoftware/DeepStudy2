package hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.apache.log4j.MDC;

public class MdcHystrixCommand extends HystrixCommand<String> {

    public MdcHystrixCommand(String commandKey ) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("mdc")).andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withRequestLogEnabled(true)
                ));
    }

    @Override
    protected String run() throws Exception {

//        Thread.currentThread().getName() + " " +
        return (String) MDC.get("traceId");

    }

}