package com.blog.rxjava;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hg on 2017/9/16.
 */
public class RxJavaTest {

    @Test
    public void rxBasic() {
        //step 1
        Observable.OnSubscribe onSubscribe1 = new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onCompleted();
            }

            @Override
            public String toString() {
                return "onSubscribe1";
            }
        };
        //step 2
        Observable observable1 = Observable.create(onSubscribe1);
        //step 3
        Subscriber<String> subscriber1 = new Subscriber<String>() {
            @Override
            public void onStart() {
                System.out.println("subscriber start.");
            }

            @Override
            public void onNext(String s) {
                System.out.println("subscriber next:" + s);
            }

            @Override
            public void onCompleted() {
                System.out.println("subscriber complete.");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public String toString() {
                return "subscriber1";
            }
        };
        //step 4
        observable1.subscribe(subscriber1);
        onSubscribe1.call(subscriber1);
    }
}
