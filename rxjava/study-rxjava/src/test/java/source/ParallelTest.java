package source;

import org.junit.Test;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Random;

/**
 * RxJava并发处理
 * https://tomstechnicalblog.blogspot.jp/2015/11/rxjava-achieving-parallelization.html
 */
public final class ParallelTest {

    private static final Random rand = new Random();

    // 没有并行
    @Test
    public void notPar() {
        Observable<Integer> vals = Observable.range(1, 9);
        vals.subscribeOn(Schedulers.computation())
                .map(i -> intenseCalculation(i))
                .subscribe(val -> System.out.println("Subscriber received " + val + " on " + Thread.currentThread().getName()));
        waitSleep();
    }

    //同一个Observable的onNext方法是串行的
    // 通过flatMap将Observable独立运行在不同线程中
    @Test
    public void parProc() {
        Observable<Integer> vals = Observable.range(1, 10);
        vals.flatMap(val -> Observable.just(val)
                .subscribeOn(Schedulers.computation())
                .map(i -> intenseCalculation(i))
        ).subscribe(val -> System.out.println("Subscriber received "
                + val + " on "
                + Thread.currentThread().getName()));

        waitSleep();
    }

    @Test
    public void parProcAndGet() {
        Observable<Integer> vals = Observable.range(1, 9);

        vals.flatMap(val -> Observable.just(val)
                .subscribeOn(Schedulers.computation())
                .map(i -> intenseCalculation(i))
        ).toList()
                .subscribe(val -> System.out.println("Subscriber received "
                        + val + " on "
                        + Thread.currentThread().getName()));

        waitSleep();
    }


    public static void waitSleep() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int intenseCalculation(int i) {
        try {
            System.out.println("Calculating " + i + " on " + Thread.currentThread().getName());
            Thread.sleep(randInt(1000, 5000));
            return i;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
