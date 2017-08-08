package source;

import org.junit.Test;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * http://www.licheedev.com/2015/10/26/rxjava-diao-bao-le/
 * http://blog.chengyunfeng.com/?p=978
 * Created by hg on 2017/7/20.
 */
public class SchedulerWorkerDemo {

//    任务被分配到其指定的线程中
    @Test
    public void tt() throws InterruptedException {
        Scheduler scheduler = Schedulers.newThread();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(() -> {
            printThread("task run.");
        });

        TimeUnit.MILLISECONDS.sleep(200);
    }

    // 延时任务
    @Test
    public void delay() throws InterruptedException {
        Scheduler scheduler = Schedulers.newThread();
        long start = System.currentTimeMillis();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " " + (System.currentTimeMillis() - start));
                },
                1, TimeUnit.SECONDS);
        worker.schedule(
                () -> System.out.println(Thread.currentThread().getName() + " " +(System.currentTimeMillis() - start)),
                1, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(5);
    }

//    Scheduler.Worker 继承至 Subscription。调用 unsubscribe 函数可以取消队列中的任务
    @Test
    public void cancel() throws InterruptedException {
        Scheduler scheduler = Schedulers.newThread();
        long start = System.currentTimeMillis();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(
                () -> {
                    System.out.println(System.currentTimeMillis()-start);
                    worker.unsubscribe();
                },
                1, TimeUnit.SECONDS);
        worker.schedule(
                () -> System.out.println(System.currentTimeMillis()-start),
                1, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(2);
    }

    // 调度递归的方法调用
    @Test
    public void workIn() throws InterruptedException {
        Scheduler.Worker worker = Schedulers.newThread().createWorker();
        worker.schedule(new Action0() {
            @Override
            public void call() {
                System.out.println("call");
                // recurse until unsubscribed (schedule will do nothing if unsubscribed)
                worker.schedule(this);
            }
        });
        TimeUnit.SECONDS.sleep(2);
        worker.unsubscribe();
    }

    public static void printThread(String name) {
        System.out.println(Thread.currentThread().getName()+":" + name);
    }
}
