package com.hg.cat.demo;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

import java.util.concurrent.Callable;

public class CatHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
@Override
public Callable wrapCallable(Callable callable) {
return new CatHystrixConcurrencyCallable(callable);
}



}