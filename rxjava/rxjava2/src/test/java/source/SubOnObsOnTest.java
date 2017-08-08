package source;

import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * https://tomstechnicalblog.blogspot.jp/2016/02/rxjava-understanding-observeon-and.html
 * Created by hg on 2017/7/21.
 */
public class SubOnObsOnTest {

    @Test
    public void subscribeOn() throws InterruptedException {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        System.out.println(number + Thread.currentThread().getName());
                    }
                });
        TimeUnit.MILLISECONDS.sleep(200);
    }

    @Test
    public void observeOn() throws InterruptedException {

        Observable<Integer> source = Observable.range(1,10);

        source.map(i -> i * 100)
                .doOnNext(i ->
                        System.out.println("Emitting " + i
                                + " on thread " + Thread.currentThread().getName()))
                .observeOn(Schedulers.computation())
                .map(i -> i * 10)
                .subscribe(i -> System.out.println("Received " + i + " on thread "
                        + Thread.currentThread().getName()));

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
