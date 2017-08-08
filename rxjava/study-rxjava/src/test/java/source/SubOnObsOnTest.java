package source;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * https://tomstechnicalblog.blogspot.jp/2016/02/rxjava-understanding-observeon-and.html
 * Created by hg on 2017/7/21.
 */
@Slf4j
public class SubOnObsOnTest {

    @Test
    public void subscribeOn() throws InterruptedException {

        Observable<String> observable1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println(Thread.currentThread().getName() + ":OnSub");
                subscriber.onNext("hello");
            }
        });

        Observable<String> observable2 = observable1.subscribeOn(Schedulers.io());

        Subscriber<String> subscriber1 = new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println(Thread.currentThread().getName() + ":" +s);
            }

            @Override
            public String toString(){
                return "subscriber1";
            }
        };
        observable2.subscribe(subscriber1);
        TimeUnit.MILLISECONDS.sleep(200);
    }

    @Test
    public void observeOn() throws InterruptedException {

        Observable<String> observable1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println(Thread.currentThread().getName() + ":OnSub");
                subscriber.onNext("hello");
            }
        });

//        observable1 = observable1.subscribeOn(Schedulers.computation());
        Observable<String> observable2 = observable1.observeOn(Schedulers.io());

        observable2.subscribe(new Action1<String>() {
            @Override
            public void call(String str) {
                System.out.println( Thread.currentThread().getName() + ":" + str);
            }
        });

        sleep(3000);
    }


    @Test
    public void test() throws InterruptedException {
        Observable.just("a","b","c")
            .doOnNext(new Action1<String>() {
                @Override
                public void call(String s) {
                    System.out.println("emmit" + s);
                }
            })
//                .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .map(input -> {
                System.out.println("map in thread: " + Thread.currentThread().getName());
                return input + " -> map";
            })
            .observeOn(Schedulers.newThread())
            .map(input -> {
                System.out.println("map2 in thread: " + Thread.currentThread().getName());
                return input + " -> map2 ";
            })
            .observeOn(Schedulers.newThread())
            .subscribe(input -> {
                System.out.println("subscribe in thread: " + Thread.currentThread().getName());
                System.out.println(input);
            });

        Thread.sleep(200000);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
