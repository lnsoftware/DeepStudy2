package com.zhiyin.hystrix.core.property;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixThreadPoolMetrics;

/**
 * Created by hg on 2017/4/24.
 */
public class CommandUtil {

    public static CommandStatus status(HystrixCommand c){

        CommandStatus s = new CommandStatus();
        s.setCircuitBreakerOpen( c.isCircuitBreakerOpen() );

        s.setExecutionTimeInMilliseconds( c.getExecutionTimeInMilliseconds() );
        s.setFailedExecutionException( c.getFailedExecutionException() );
        s.setResponseRejected(c.isResponseRejected());
        s.setResponseShortCircuited(c.isResponseShortCircuited());
        s.setResponseTimedOut(c.isResponseTimedOut());
        s.setExecutionTimeoutInMilliseconds(  c.getProperties().executionTimeoutInMilliseconds().get());
        int pool = HystrixThreadPoolMetrics.getInstance(c.getThreadPoolKey()).getCurrentCorePoolSize().intValue();
        s.setCorePoolSize(pool);
        return s;
    }


}
