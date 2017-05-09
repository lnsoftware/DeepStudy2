//package com.hg.cat.demo;
//
//import com.dianping.cat.Cat;
//import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
//import java.util.concurrent.Callable;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class CatHystrixConcurrencyStrategy3 extends HystrixConcurrencyStrategy {
//    @Override
//    public Callable wrapCallable(Callable callable) {
//        return new CatHystrixConcurrencyCallable3(callable);
//    }
//
//}
//
// class CatHystrixConcurrencyCallable3<K> implements Callable<K> {
//
//    private Logger logger = LoggerFactory.getLogger(CatHystrixConcurrencyCallable2.class);
//
//    private final Callable<K> actual;
//
////    private final Cat.Context context;
//
//    public CatHystrixConcurrencyCallable3(Callable<K> actual ) {
//        this.actual = actual;
//    }
//
//    @Override
//    public K call() throws Exception {
//        try {
//            Cat.logRemoteCallServer(context);
//        }catch (Exception e){
//        }
//
//        try {
//            return actual.call();
//        } finally {
//            Cat.getManager().reset();
//        }
//    }
//}