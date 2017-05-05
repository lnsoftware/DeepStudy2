import com.zhiyin.rpc.httpinvoker.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.httpinvoker.CommonsHttpInvokerRequestExecutor;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2016/12/16.
 */
@Slf4j
public class HttpclientTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring-httpinvoker.xml");
        final DemoService remotDemoService = context.getBean("remoteDemoService2",DemoService.class);

        CommonsHttpInvokerRequestExecutor httpInvokerRequestExecutor = context.getBean("httpInvokerRequestExecutor", CommonsHttpInvokerRequestExecutor.class);

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 50; i++) {
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            System.out.println(remotDemoService.sleep(800));
                        } catch (Exception e) {
                            System.out.println("error");
                        }

                        try {
                            Thread.sleep(RandomUtils.nextInt(400, 500));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
        }


        // 10s后更新httpClient
        TimeUnit.SECONDS.sleep(10);

        HttpClientParams httpClientParams = new HttpClientParams();
        httpClientParams.setConnectionManagerTimeout(500);

        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setConnectionTimeout(500);
        params.setSoTimeout(500);
        params.setDefaultMaxConnectionsPerHost(80);
        params.setMaxTotalConnections(100);

        MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
        manager.setParams(params);

        HttpClient client = new HttpClient(httpClientParams, manager);


        httpInvokerRequestExecutor.setHttpClient(client);


    }

}