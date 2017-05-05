package os;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Apple on 2016/10/16.
 */
public class Handler {

    private ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);

    public void loop() {
        for (; ; ) {
            Runnable runnable;
            try {
                runnable = queue.take();// 没有数据则一直阻塞，直到有数据自动唤醒
            } catch (InterruptedException e) {
                return;
            }
            if (runnable == null) {
                return;
            }
            runnable.run();
        }
    }

    public void post(Runnable runnable) {
        try {
            queue.put(runnable);// 没有空间则一直阻塞，直到有空间
        } catch (InterruptedException e) {
            return;
        }
    }
}
