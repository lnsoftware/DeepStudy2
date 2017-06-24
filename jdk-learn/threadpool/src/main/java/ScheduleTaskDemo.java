import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2017/6/16.
 */
public class ScheduleTaskDemo {

    public static void main(String[] args) {

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("timer task.");
            }
        },5,5, TimeUnit.SECONDS);

    }


    /**
     * 创建一个可缓存的无界线程池，该方法无参数。当线程池中的线程空闲时间超过60s则会自动回收该线程，当任务超过线程池的线程数则创建新线程。线程池的大小上限为Integer.MAX_VALUE，可看做是无限大。
     */
    public void cachedThreadPoolDemo(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            final int index = i;

            cachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+", index="+index);
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
