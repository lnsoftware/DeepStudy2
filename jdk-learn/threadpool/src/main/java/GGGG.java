import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by wangqinghui on 2017/12/21.
 */
public class GGGG {

    private static final int THREAD_POOL_LIST_SIZE = 50;

    public static final int CPU_CORE_NUM = Runtime.getRuntime().availableProcessors();

    public static final int THREAD_POOL_CORE_THREAD_SIZE = CPU_CORE_NUM;

    public static final int THREAD_POLL_MAX_THREAD_SIZE = CPU_CORE_NUM * 20;

    public static final int PARTITION_SIZE = 10;

    //线程数 = CPU可用核心数/(1 - 阻塞系数)，其中阻塞系数的取值在0和1之间。任务有50%的时间处于阻塞状态，则阻塞系数为0.5
    public static ExecutorService executorService = new ThreadPoolExecutor(THREAD_POOL_CORE_THREAD_SIZE, THREAD_POLL_MAX_THREAD_SIZE,
            100L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(THREAD_POOL_LIST_SIZE), new MsgRejectedPolicy());


    public static void main(String[] args) {

        while (true){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                System.out.println();
                }
            });
        }

    }
}


class MsgRejectedPolicy implements RejectedExecutionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MsgRejectedPolicy.class);

    public MsgRejectedPolicy() { }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("fail");
        //如果队列已满，新来的任务将被丢弃，并打印当前线程池当前活跃线程数和等待队列当前长度
        logger.warn("task discard,thread pool active count:{},blocking queue size:{}",executor.getActiveCount(),executor.getQueue().size());
    }
}




