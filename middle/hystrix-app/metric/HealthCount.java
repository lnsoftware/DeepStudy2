package oceanus.sdk.meta.metric;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhanjun on 2017/4/23.
 */
public class HealthCount {

    private AtomicInteger successCount = new AtomicInteger();
    private AtomicInteger errorCount = new AtomicInteger();
    private ConcurrentHashMap<Class<? extends Throwable>, AtomicInteger> exceptionCount = new ConcurrentHashMap<>();

    public void clear() {
        errorCount.set(0);
        successCount.set(0);
        exceptionCount.clear();
    }

    /**
     * 统计异常总数和各个异常的数量
     * @param throwable
     */
    public void markFailed(Throwable throwable) {
        errorCount.incrementAndGet();
        Class<? extends Throwable> errorType = throwable.getClass();
        AtomicInteger count = exceptionCount.get(errorType);
        if (count == null) {
            count = new AtomicInteger();
            AtomicInteger count0 = exceptionCount.putIfAbsent(errorType, count);
            if (count0 != null) {
                count = count0;
            }
        }
        count.incrementAndGet();
    }

    public void markSuccess() {
        successCount.incrementAndGet();
    }

    public int getErrorCount() {
        return errorCount.get();
    }

    public int getSuccessCount() {
        return successCount.get();
    }

    public ConcurrentHashMap<Class<? extends Throwable>, AtomicInteger> getExceptionCount() {
        return exceptionCount;
    }
}
