package com.blog.rxjava;

import org.junit.Test;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangqinghui on 2017/9/16.
 */
public class SubscribeOnTest {

    @Test
    public void scheduler() throws InterruptedException {
Scheduler scheduler = Schedulers.newThread();
Scheduler.Worker worker = scheduler.createWorker();
Action0 action0 = new Action0() {
    @Override
    public void call() {
        System.out.println(Thread.currentThread().getName());
    }
};
worker.schedule(action0);
TimeUnit.MILLISECONDS.sleep(200);
    }

    @Test
    public void subscribeOnTest() throws InterruptedException {
//step 1
Observable.OnSubscribe onSubscribe1 = new Observable.OnSubscribe<String>() {
    @Override
    public void call(Subscriber<? super String> subscriber) {
        subscriber.onNext("hello");
        subscriber.onCompleted();
    }
    @Override
    public String toString(){
        return "onSubscribe1";
    }
};
//step2
Observable<String> observable1 = Observable.create(onSubscribe1);
//step3
Observable<String> observable2 = observable1.subscribeOn(Schedulers.newThread());
//step4
Subscriber<String> subscriber1 = new Subscriber<String>() {
    @Override
    public void onNext(String s) {
        System.out.println(Thread.currentThread().getName() + ":" + s);
    }
    @Override
    public void onCompleted() {
    }
    @Override
    public void onError(Throwable e) {
    }
    @Override
    public String toString() {
        return "subscriber1";
    }
};
//step5
observable2.subscribe(subscriber1);
TimeUnit.MILLISECONDS.sleep(100);
    }

}
