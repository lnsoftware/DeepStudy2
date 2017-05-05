import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.contrib.codahalemetricspublisher.HystrixCodaHaleMetricsPublisher;
import com.netflix.hystrix.examples.demo.HystrixCommandDemo;
import com.netflix.hystrix.strategy.HystrixPlugins;
import org.elasticsearch.metrics.ElasticsearchReporter;
import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试Hystrix的Reporter
 * Created by hg on 2017/2/6.
 */
public class HystrixReporterStandaloneTest {

    @Test
    public void test() throws Exception {


        new HystrixCommandDemo().runSimulatedRequestOnThread();

        MetricRegistry metricRegistry = new MetricRegistry();
        HystrixCodaHaleMetricsPublisher publisher = new HystrixCodaHaleMetricsPublisher(metricRegistry);
        HystrixPlugins.getInstance().registerMetricsPublisher(publisher);

        ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
//        int period = 1000
        reporter.start(1, TimeUnit.SECONDS);

//        ElasticsearchReporter elasticsearchReporter = ElasticsearchReporter.forRegistry(metricRegistry)
//                .hosts("localhost:9200")
//                .build();
//        elasticsearchReporter.start(1, TimeUnit.SECONDS);

        HelloWorldCommand helloWorldCommand = null;

        for (int i = 0; i < 51; i++) {

            helloWorldCommand = new HelloWorldCommand("helloworld");
            helloWorldCommand.execute();

            Meter requests = metricRegistry.meter("requests");
            requests.mark();

            Thread.sleep(20);
        }

    }


    static class HelloWorldCommand extends HystrixCommand<String> {
        private final String name;

        public HelloWorldCommand(String name) {
            super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("helloWorldGroup")).andCommandKey(HystrixCommandKey.Factory.asKey("helloWorldKey")));
            this.name = name;
        }

        @Override
        protected String run() {
            return "Hello " + name + " thread:" + Thread.currentThread().getName();
        }

    }
}