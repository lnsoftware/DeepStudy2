
import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.contrib.codahalemetricspublisher.HystrixCodaHaleMetricsPublisher;
import com.netflix.hystrix.strategy.HystrixPlugins;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * 将Hystrix数据使用引入到DropwizardMetrics
 */
public class HystrixCodaHaleMetricsPublisherCommandTest {

    private final MetricRegistry metricRegistry = new MetricRegistry();

    @Before
    public void setup() {
        HystrixPlugins.getInstance().registerMetricsPublisher(new HystrixCodaHaleMetricsPublisher(metricRegistry));
    }

    @After
    public void teardown() {
        HystrixPlugins.reset();
    }

    @Test
    public void commandMaxActiveGauge() {
        final HystrixCommandKey hystrixCommandKey = HystrixCommandKey.Factory.asKey("test");
        final HystrixCommandGroupKey hystrixCommandGroupKey = HystrixCommandGroupKey.Factory.asKey("test");

        new HystrixCommand<Void>(HystrixCommand.Setter
                .withGroupKey(hystrixCommandGroupKey)
                .andCommandKey(hystrixCommandKey)) {
            @Override
            protected Void run() throws Exception {
                return null;
            }
        }.execute();

//        metricRegistry.get

        for (Map.Entry<String, Gauge> entry : metricRegistry.getGauges().entrySet()) {
            if( entry.getValue().getValue() instanceof Boolean){
                System.out.println("boolean " +entry.getKey() +" " + entry.getValue().getValue());
            }
            if( entry.getValue().getValue() instanceof String){
                System.out.println("string " +entry.getKey() +" " + entry.getValue().getValue());
            }
        }

        System.out.println("no counter");
        for (Map.Entry<String, Counter> entry : metricRegistry.getCounters().entrySet()) {
            System.out.println(entry.getKey() +" " + entry.getValue().getCount());
        }
    }
}