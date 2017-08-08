package hystrix;

import com.netflix.hystrix.strategy.HystrixPlugins;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Test;
import org.slf4j.MDC;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * 测试Hystrix MDC传参
 * Created by hg on 2016/12/22.
 */
public class MdcTest {

    static ExecutorService executorService = Executors.newFixedThreadPool(30);

    @Test
    public void basicTest() {
        MDC.put("traceId", UUID.randomUUID().toString());
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new MdcHystrixConcurrencyStrategy());
        MdcHystrixCommand command = new MdcHystrixCommand("mdc");
        String traceId = command.execute();
        assertThat(traceId).isEqualTo(MDC.get("traceId"));
    }

    @Test
    public void batchTest() {

        HystrixPlugins.getInstance().registerConcurrencyStrategy(new MdcHystrixConcurrencyStrategy());

        for (int i = 0; i < 20; i++) {
            MdcTraceTask task = new MdcTraceTask("" + i);
            executorService.submit(task);
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
