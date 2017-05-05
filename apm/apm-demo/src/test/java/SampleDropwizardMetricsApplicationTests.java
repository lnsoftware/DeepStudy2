import com.codahale.metrics.MetricRegistry;
import com.hg.sb.act.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SampleDropwizardMetricsApplicationTests {

    @Autowired
    private MetricRegistry registry;

    @Autowired
    private GaugeService gauges;

    @Test
    public void timerCreated() {
        this.gauges.submit("timer.test", 1234);
        assertThat(this.registry.getTimers().get("timer.test").getCount()).isEqualTo(1);
    }


}