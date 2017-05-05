package hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

import java.util.concurrent.Callable;

public class MdcHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
@Override
public Callable wrapCallable(Callable callable) {
return new MdcHystrixContextCallable(callable);
}
}