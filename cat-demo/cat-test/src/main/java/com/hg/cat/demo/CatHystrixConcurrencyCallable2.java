package com.hg.cat.demo;

import com.dianping.cat.Cat;
import com.dianping.cat.message.spi.MessageTree;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatHystrixConcurrencyCallable2<K> implements Callable<K> {

    private Logger logger = LoggerFactory.getLogger(CatHystrixConcurrencyCallable2.class);

    private final Callable<K> actual;

    private final Cat.Context context;

    public CatHystrixConcurrencyCallable2(Callable<K> actual ) {
        this.actual = actual;
        // 设置RemoteContext
        this.context = new RemoteCatContext();
        System.out.println("logRemoteCallClient");
        Cat.logRemoteCallClient(this.context);
    }

    @Override
    public K call() throws Exception {
         try {
             Cat.logRemoteCallServer(context);
         }catch (Exception e){
         }

        try {
            return actual.call();
        } finally {
             Cat.getManager().reset();
        }
    }
}