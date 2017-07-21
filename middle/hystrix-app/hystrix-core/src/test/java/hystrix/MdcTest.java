package hystrix;

import com.netflix.hystrix.strategy.HystrixPlugins;
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
    public void test() {

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


    @Test
    public void test2() {
        MDC.put("traceId", "sss");
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new MdcHystrixConcurrencyStrategy());
        MdcHystrixCommand d = new MdcHystrixCommand("mdc");
        String retTid = d.execute();
        System.out.println(retTid);
    }
}
