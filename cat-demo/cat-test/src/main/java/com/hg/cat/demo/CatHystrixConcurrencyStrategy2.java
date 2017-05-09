package com.hg.cat.demo;

import com.dianping.cat.Cat;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import java.util.concurrent.Callable;

public class CatHystrixConcurrencyStrategy2 extends HystrixConcurrencyStrategy {
    @Override
    public Callable wrapCallable(Callable callable) {
        return new CatHystrixConcurrencyCallable2(callable);
    }

}