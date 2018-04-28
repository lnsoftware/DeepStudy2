package oceanus.sdk.meta.metric;


/**
 * @author zhanjun on 2017/4/23.
 */
public class HealthCountBucket {

    private static final int NUM = 100;
    private HealthCount[] healthCounts = new HealthCount[NUM];
    private CircuitBreakerProperties properties;

    public HealthCountBucket(CircuitBreakerProperties properties) {
        this.properties = properties;
        for (int i = 0; i < NUM; i++) {
            healthCounts[i] = new HealthCount();
        }
    }

    /**
     * @param throwable
     */
    public void mark(Throwable throwable) {
        int index = (int)(System.currentTimeMillis() / 1000) % 100;
        HealthCount healthCount = healthCounts[index];
        if (throwable == null) {
            healthCount.markSuccess();
        } else {
            healthCount.markFailed(throwable);
        }
    }

    public void reset() {
        for (int i = 0; i < NUM; i++) {
            healthCounts[i].clear();
        }
    }

    public HealthCount get(int index) {
        return healthCounts[index];
    }

    public int getRollingStatsTime() {
        return 10;
//        return properties.getRollingStatsTime();
    }
}
