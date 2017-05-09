import java.util.concurrent.locks.LockSupport;

/**
 *
 * 线程如果因为调用park而阻塞的话，能够响应中断请求(中断状态被设置成true)，但是不会抛出InterruptedException
 * Created by wangqinghui on 2017/6/13.
 */
public class LockSupportTest {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("ss");
                    LockSupport.park(this);
                    if(Thread.interrupted()){
                        System.out.println("interrupte");
                        return;
                    }
                }
            }
        };
        Thread t1 = new Thread(runnable);

        t1.start();

        t1.interrupt();
        LockSupport.unpark(t1);

    }

    public static void t2() throws Exception {
        Thread t = new Thread(new Runnable()
        {
            private int count = 0;

            @Override
            public void run()
            {
                long start = System.currentTimeMillis();
                long end = 0;

                while ((end - start) <= 1000)
                {
                    count++;
                    end = System.currentTimeMillis();
                }

                System.out.println("after 1 second.count=" + count);

                //等待或许许可
                LockSupport.park();
                System.out.println("thread over." + Thread.currentThread().isInterrupted());

            }
        });

        t.start();

        Thread.sleep(2000);

        // 中断线程
        t.interrupt();


        System.out.println("main over");
    }
}
