import com.zhiyin.tp.monitor.jmx.DemoIOTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hg on 2017/6/24.
 */
public class ThreadPoolJmxTest {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("tp-jmx-demo.xml");

        ExecutorService executorService = context
            .getBean("executorService", ExecutorService.class);

        for (int i = 0; i < 1000000; i++) {
            executorService.execute(new DemoIOTask());
            TimeUnit.MILLISECONDS.sleep(50);
        }
        try {
            Thread.sleep(1000 * 60 * 5);
        } catch (final Throwable t) {
        }

        executorService.shutdown();
    }
}
