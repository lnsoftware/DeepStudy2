package hystrix;

import com.netflix.hystrix.strategy.HystrixPlugins;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试Hystrix MDC传参
 * Created by wangqinghui on 2016/12/22.
 */
public class MdcTest {

    static ExecutorService executorService = Executors.newFixedThreadPool(30);

    @Test
    public void test() {

        HystrixPlugins.getInstance().registerConcurrencyStrategy(new MdcHystrixConcurrencyStrategy());

        for(int i=0;i<20;i++){
            MdcTraceTask task = new MdcTraceTask(""+i);
            executorService.submit( task );
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
