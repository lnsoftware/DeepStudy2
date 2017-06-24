
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ThreadFactory Demo
 * 参考Apollo
 */
public class DemoThreadFactory implements ThreadFactory {

    private static final Logger log = LoggerFactory.getLogger(DemoThreadFactory.class);
    private static final ThreadGroup threadGroup = new ThreadGroup("hg");
    private final AtomicLong threadNumber = new AtomicLong(1);
    private final String namePrefix;
    private final boolean daemon;

    private DemoThreadFactory(String namePrefix, boolean daemon) {
        this.namePrefix = namePrefix;
        this.daemon = daemon;
    }

    public static ThreadGroup getThreadGroup() {
        return threadGroup;
    }

    public static ThreadFactory create(String namePrefix, boolean daemon) {
        return new DemoThreadFactory(namePrefix, daemon);
    }

    public static boolean waitAllShutdown(int timeoutInMillis) {
        ThreadGroup group = getThreadGroup();
        Thread[] activeThreads = new Thread[group.activeCount()];
        group.enumerate(activeThreads);
        Set<Thread> alives = new HashSet<Thread>(Arrays.asList(activeThreads));
        Set<Thread> dies = new HashSet<Thread>();
        log.info("Current ACTIVE thread count is: {}", alives.size());
        long expire = System.currentTimeMillis() + timeoutInMillis;
        while (System.currentTimeMillis() < expire) {
            classify(alives, dies, new ClassifyStandard<Thread>() {
                @Override
                public boolean satisfy(Thread thread) {
//                    System.out.println(thread.isAlive() +""+ thread.isDaemon());
                    return !thread.isAlive() || thread.isInterrupted() || thread.isDaemon();
                }
            });
            if (alives.size() > 0) {
                log.info("Alive threads: {}", alives);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    // ignore
                }
            } else {
                log.info("All threads are shutdown.");
                return true;
            }
        }
        log.warn("Some threads are still alive but expire time has reached, alive threads: {}",
                alives);
        return false;
    }

    private static <T> void classify(Set<T> src, Set<T> des, ClassifyStandard<T> standard) {
        Set<T> set = new HashSet<T>();
        for (T t : src) {
            if (standard.satisfy(t)) {
                set.add(t);
            }
        }
        src.removeAll(set);
        des.addAll(set);
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(threadGroup, runnable,//
                threadGroup.getName() + "-" + namePrefix + "-" + threadNumber.getAndIncrement());
        thread.setDaemon(daemon);
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }

    private static interface ClassifyStandard<T> {
        boolean satisfy(T thread);
    }
}
