import org.junit.Test;

import java.sql.Time;
import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * Created by hg on 2017/6/24.
 */
public class DemoThreadFactoryTest {

    ThreadFactory threadFactory = DemoThreadFactory.create("demo", true);

    @Test
    public void testCreate() throws Exception {

        // 用法1
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1, threadFactory);
        // 用法2
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
    }


    @Test
    public void testWaitAllShutdown() throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(1, threadFactory);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("task start.");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task end.");
            }
        });

//        executor.shutdown();
//        ((DemoThreadFactory)threadFactory).waitAllShutdown(6000);

        TimeUnit.SECONDS.sleep(6);
    }
}