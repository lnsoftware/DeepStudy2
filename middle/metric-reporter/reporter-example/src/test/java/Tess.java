import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import org.elasticsearch.metrics.ElasticsearchReporter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2017/1/23.
 */
public class Tess {

    public static void main(String[] args) throws IOException, InterruptedException {

        final MetricRegistry registry = new MetricRegistry();
        ElasticsearchReporter reporter = ElasticsearchReporter.forRegistry(registry)
                .index("metrics")
                .hosts("localhost:9200")
                .build();
        reporter.start(6, TimeUnit.SECONDS);


        final Meter incomingRequestsMeter = registry.meter("incoming-http-requests");
        while (true) {
            incomingRequestsMeter.mark(1);
            Thread.sleep(1000);
            System.out.println("run");
        }
// in your app code

    }
}
