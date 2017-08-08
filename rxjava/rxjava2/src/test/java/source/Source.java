package source;

import littlerx.Func1;
import org.junit.Test;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;


/**
 * Created by hg on 2017/4/11.
 */
public class Source {

    public static void main(String[] args) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxJava!");
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {

            @Override
            public void onStart() {
                System.out.println("start");
            }

            @Override
            public void onCompleted() {
                System.out.println("completed!");
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });



    }


    @Test
    public void mapOp(){

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxJava!");
                subscriber.onCompleted();
            }
        }).map(new rx.functions.Func1<String, String>() {
            @Override
            public String call(String s) {
                return s.toUpperCase();
            }
        }).
                subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("completed!");
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });


    }


//    有一种场景，比如说请求到数据后写入缓存，但是不希望订阅者去处理，因为如果多处订阅必然会产生重复代码并且可能阻塞主线程，doOn的系列操作就派上了用场
    @Test
    public void doOn() {

        Observable.just("a1","a2")
                .doOnEach(new Action1<Notification<? super String>>() {
                    @Override
                    public void call(Notification<? super String> notification) {
                        System.out.println("" + notification);
                    }
                })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //发射成功后需要的操作
                        System.out.println("doOnNext:" + s);
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnCompleted");
                    }
                })

        .subscribe(
                new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        System.out.println("start");
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("completed!");
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });

    }
}
