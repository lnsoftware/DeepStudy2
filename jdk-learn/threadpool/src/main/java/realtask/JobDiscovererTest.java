package realtask;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JobDiscovererTest {

    public static void main(String... args) {
        final CountDownLatch latch = new CountDownLatch(1);
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException consumeAndExit) {
                        System.out.println(Thread.currentThread().getName() +
                                " was interrupted - exiting");
                    }
                }

                @Override
                public String toString() {
                    return "Runnable: " + finalI;
                }
            });
            pool.submit(new Callable<String>() {
                @Override
                public String call() throws InterruptedException {
                    latch.await();
                    return "success";
                }

                @Override
                public String toString() {
                    return "Callable: " + finalI;
                }
            });
        }

        // Note: the Runnables returned from shutdownNow are NOT
        // the same objects as we submitted to the pool!!!
        List<Runnable> tasks = pool.shutdownNow();

        System.out.println("Tasks from ThreadPool");
        System.out.println("=====================");
        for (Runnable task : tasks) {
            System.out.println("Task from ThreadPool " + task);
        }

        System.out.println();
        System.out.println("Using our JobDiscoverer");
        System.out.println("=======================");

        for (Runnable task : tasks) {
            Object realTask = JobDiscoverer.findRealTask(task);
            System.out.println("Real task was actually " + realTask);
        }
    }
}
  