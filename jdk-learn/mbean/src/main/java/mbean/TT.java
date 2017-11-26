package mbean;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.Thread.State.*;

/**
 * Created by wangqinghui on 2017/11/22.
 */
public class TT {

    public static void main(String[] args) {
        System.out.println(collectThreadInfo());
    }


    private static Map<String, Number> collectThreadInfo() {
        final ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        Map<String, Number> map = new LinkedHashMap<String, Number>();
        map.put("jvm.thread.count", threadBean.getThreadCount());
        map.put("jvm.thread.daemon.count", threadBean.getDaemonThreadCount());
        map.put("jvm.thread.totalstarted.count", threadBean.getTotalStartedThreadCount());
        ThreadInfo[] threadInfos = threadBean.getThreadInfo(threadBean.getAllThreadIds());

        int newThreadCount = 0;
        int runnableThreadCount = 0;
        int blockedThreadCount = 0;
        int waitThreadCount = 0;
        int timeWaitThreadCount = 0;
        int terminatedThreadCount = 0;

        if (threadInfos != null) {
            for (ThreadInfo threadInfo : threadInfos) {
                if (threadInfo != null) {
                    switch (threadInfo.getThreadState()) {
                        case NEW:
                            newThreadCount++;
                            break;
                        case RUNNABLE:
                            runnableThreadCount++;
                            break;
                        case BLOCKED:
                            blockedThreadCount++;
                            break;
                        case WAITING:
                            waitThreadCount++;
                            break;
                        case TIMED_WAITING:
                            timeWaitThreadCount++;
                            break;
                        case TERMINATED:
                            terminatedThreadCount++;
                            break;
                        default:
                            break;
                    }
                } else {
                    /*
                     * If a thread of a given ID is not alive or does not exist,
                     * the corresponding element in the returned array will,
                     * contain null,because is mut exist ,so the thread is terminated
                     */
                    terminatedThreadCount++;
                }
            }
        }

        map.put("jvm.thread.new.count", newThreadCount);
        map.put("jvm.thread.runnable.count", runnableThreadCount);
        map.put("jvm.thread.blocked.count", blockedThreadCount);
        map.put("jvm.thread.waiting.count", waitThreadCount);
        map.put("jvm.thread.time_waiting.count", timeWaitThreadCount);
        map.put("jvm.thread.terminated.count", terminatedThreadCount);

        long[] ids = threadBean.findDeadlockedThreads();
        map.put("jvm.thread.deadlock.count", ids == null ? 0 : ids.length);

        return map;
    }


}
