import org.apache.tomcat.util.threads.TaskQueue;
import org.apache.tomcat.util.threads.TaskThreadFactory;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2017/6/24.
 */
public class Tomcat {

    public static void main(String[] args) throws InterruptedException {
        TaskQueue taskqueue = new TaskQueue();
        TaskThreadFactory tf = new TaskThreadFactory("hg-exec-", true,5);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, taskqueue, tf);
        taskqueue.setParent( (ThreadPoolExecutor) executor);

        for (int i = 0; i < 10 ; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        TimeUnit.MINUTES.sleep(10);
    }
}
