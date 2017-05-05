
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestJedisPipeline {
    private static final int NUM = 512;
    private static final int MAX = 1000000; // 100W

    private static JedisPool redisPool;
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    protected static final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(
            MAX); // 100W
    private static boolean finished = false;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxActive(64);
        config.setMaxIdle(64);

        try {
            redisPool = new JedisPool(config, "192.168.192.8", 6379, 10000,
                    null, 0);
        } catch (Exception e) {
            System.err.println("Init msg redis factory error! " + e.toString());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("prepare test data 100W");
        prepareTestData();
        System.out.println("prepare test data done!");

        // 生产者，模拟请求100W次
        pool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < MAX; i++) {
                    if (i % 3 == 0) {
                        queue.offer("del_key key_" + i);
                    } else {
                        queue.offer("get_key key_" + i);
                    }
                }
            }
        });

        // CPU核数*2 个工作者线程
        int threadNum = 2 * Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < threadNum; i++)
            pool.execute(new ConsumerTask());

        pool.execute(new MonitorTask());

        Thread.sleep(10 * 1000);// 10sec
        System.out.println("going to shutdown server ...");
        setFinished(true);
        pool.shutdown();

        pool.awaitTermination(1, TimeUnit.MILLISECONDS);

        System.out.println("colse!");
    }

    private static void prepareTestData() {
        Jedis redis = redisPool.getResource();
        Pipeline pipeline = redis.pipelined();

        for (int i = 0; i < MAX; i++) {
            pipeline.set("key_" + i, (i * 2 + 1) + "");

            if (i % (NUM * 2) == 0) {
                pipeline.sync();
            }
        }
        pipeline.sync();
        redisPool.returnResource(redis);
    }

    // queue monitor，生产者-消费队列监控
    private static class MonitorTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted() && !isFinished()) {
                System.out.println("queue.size = " + queue.size());
                try {
                    Thread.sleep(500); // 0.5 second
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    // consumer，消费者
    private static class ConsumerTask implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted() && !isFinished()) {
                if (queue.isEmpty()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }

                    continue;
                }

                List<String> tasks = new ArrayList<String>(NUM);
                queue.drainTo(tasks, NUM);
                if (tasks.isEmpty()) {
                    continue;
                }

                Jedis jedis = redisPool.getResource();
                Pipeline pipeline = jedis.pipelined();

                try {
                    List<Response<String>> resultList = new ArrayList<Response<String>>(
                            tasks.size());

                    List<String> waitDeleteList = new ArrayList<String>(
                            tasks.size());

                    for (String task : tasks) {
                        String key = task.split(" ")[1];
                        if (task.startsWith("get_key")) {
                            resultList.add(pipeline.get(key));
                            waitDeleteList.add(key);
                        } else if (task.startsWith("del_key")) {
                            pipeline.del(key);
                        }
                    }

                    pipeline.sync();

                    // 处理返回列表
                    for (int i = 0; i < resultList.size(); i++) {
                        resultList.get(i).get();
                        // handle value here ...
                        // System.out.println("get value " + value);
                    }

                    // 读取完毕，直接删除之
                    for (String key : waitDeleteList) {
                        pipeline.del(key);
                    }

                    pipeline.sync();
                } catch (Exception e) {
                    redisPool.returnBrokenResource(jedis);
                } finally {
                    redisPool.returnResource(jedis);
                }
            }
        }
    }

    private static boolean isFinished(){
        return finished;
    }

    private static void setFinished(boolean bool){
        finished = bool;
    }
}