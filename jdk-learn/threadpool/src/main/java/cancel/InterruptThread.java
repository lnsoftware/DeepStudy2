package cancel;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 中断线程池的某个任务.
 */
public class InterruptThread implements Runnable {

    private int num;

    public InterruptThread (int num){
        this.num = num;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread interruptThread = new Thread(new InterruptThread(1));
        ScheduledFuture<?> t = ThreadPoolUtils.getInstance().getThreadPool().scheduleAtFixedRate(interruptThread,0,2,
                TimeUnit.SECONDS);

        InterruptThread interruptThread1 = new InterruptThread(2);
        ThreadPoolUtils.getInstance().getThreadPool().scheduleAtFixedRate(interruptThread1,0,2,
                TimeUnit.SECONDS);

        InterruptThread interruptThread2 = new InterruptThread(3);
        ThreadPoolUtils.getInstance().getThreadPool().scheduleAtFixedRate(interruptThread2,0,2,
                TimeUnit.SECONDS);
        Thread.sleep(5000);

		//终止正在运行的线程interruptThread
        t.cancel(true);
        while (true){

        }
    }

    @Override
    public void run() {
        System.out.println("this is a thread" + num);
    }
}
