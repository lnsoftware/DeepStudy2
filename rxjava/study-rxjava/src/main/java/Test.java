import os.Handler;
import littlerx.Func1;
import littlerx.Observable;
import littlerx.OnSubscribe;
import littlerx.Subscriber;

/**
 * Created by iceGeneral on 2016/10/15.
 */
public class Test {
    public static void main(String[] args) {

        Observable
                .create(new OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        System.out.println(Thread.currentThread());
                        subscriber.onNext(1);
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                    }
                });
        System.out.println("-------------------------------------------------------------");
        Observable
                .create(new OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        System.out.println("create() " + Thread.currentThread());
                        subscriber.onNext(1);
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        System.out.println("map() " + Thread.currentThread());
                        return "map" + integer;
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext() " + Thread.currentThread());
                        System.out.println(s);
                    }
                });
        System.out.println("-------------------------------------------------------------");
        Observable
                .create(new OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        System.out.println("create() " + Thread.currentThread());
                        subscriber.onNext(1);
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        System.out.println("map() " + Thread.currentThread());
                        return "map" + integer;
                    }
                })
                .subscribeOnIO()
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext() " + Thread.currentThread());
                        System.out.println(s);
                        System.out.println("-------------------------------------------------------------");
                    }
                });

        Handler handler = new Handler();

        Observable
                .create(new OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        System.out.println("create() " + Thread.currentThread());
                        subscriber.onNext(1);
                    }
                })
                .observeOn(handler)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        System.out.println("map() " + Thread.currentThread());
                        return "map" + integer;
                    }
                })
                .subscribeOnIO()
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext() " + Thread.currentThread());
                        System.out.println(s);
                        System.out.println("-------------------------------------------------------------");
                    }
                });

        handler.loop();

    }

}
