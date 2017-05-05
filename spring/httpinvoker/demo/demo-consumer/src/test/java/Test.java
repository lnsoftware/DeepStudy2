
import com.zhiyin.rpc.httpinvoker.demo.service.DemoService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.httpinvoker.CommonsHttpInvokerRequestExecutor;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring-httpinvoker.xml");
        DemoService remotDemoService = context.getBean("remoteDemoService2",DemoService.class);
        System.out.println(remotDemoService.sleep(500));

        CommonsHttpInvokerRequestExecutor httpInvokerRequestExecutor = context.getBean("httpInvokerRequestExecutor", CommonsHttpInvokerRequestExecutor.class);


        HttpClientParams httpClientParams = new HttpClientParams();
        httpClientParams.setConnectionManagerTimeout(500);

        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setConnectionTimeout(500);
        params.setSoTimeout(1000);
        params.setDefaultMaxConnectionsPerHost(80);
        params.setMaxTotalConnections(100);

        MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
        manager.setParams(params);

        HttpClient client = new HttpClient(httpClientParams, manager);


        httpInvokerRequestExecutor.setHttpClient(client);


        System.out.println(remotDemoService.sleep(100000)
        );
    }
}
