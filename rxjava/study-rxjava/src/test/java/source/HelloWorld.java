package source;

import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by hg on 2016/10/18.
 */
public class HelloWorld {

    @Test
    public void rxBasic(){
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
        //step 2
        Observable observable1 = Observable.create(onSubscribe1);
        //step 3
        Subscriber<String> subscriber1 = new Subscriber<String>() {
            public void onStart() {
                System.out.println("subscriber start.");
            }
            @Override
            public void onCompleted() {
                System.out.println("subscriber complete.");
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
            @Override
            public String toString(){
                return "subscriber1";
            }
        };
        //step 4
        observable1.subscribe(subscriber1);
        onSubscribe1.call(subscriber1);
    }


    @Test
    public void create(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

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
    public void tt(){
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onCompleted() {
                System.out.println("com");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");            }
        };

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
            }
        });

        observable.subscribe(observer);

        observable.subscribe(observer);

    }


    @Test
    public void basic() {
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        )
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("end");
                    }
                })
                .lift(new Observable.Operator<String, String>() {
                    @Override
                    public Subscriber<? super String> call(Subscriber<? super String> subscriber) {
                        return new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                System.out.println("lift end");
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable throwable) {

                            }

                            @Override
                            public void onNext(String s) {
                                System.out.println("lift " + s);
                                subscriber.onNext(s);
                            }
                        };
                    }
                });

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("sub " + s);
            }

            @Override
            public void onCompleted() {
                System.out.println("sub end");
            }

            @Override
            public void onError(Throwable e) {
            }
        };

        myObservable.subscribe(mySubscriber);

    }

    @Test
    public void basic2() {
        Observable<String> myObservable = Observable.just("Hello, world!");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        myObservable.subscribe(onNextAction);

    }

    @Test
    public void basic3() {
        Observable.just("Hello, world!")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    @Test
    public void basic4() {
        Observable.just("Hello, world!")
                .subscribe(s -> System.out.println(s));
    }


}
