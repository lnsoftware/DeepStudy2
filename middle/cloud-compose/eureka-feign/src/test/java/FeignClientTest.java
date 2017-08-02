import com.netflix.discovery.EurekaClient;
import com.zhiyin.cloud.FeignApplication;
import com.zhiyin.cloud.service.ComputeClient;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FeignApplication.class)
@WebAppConfiguration
public class FeignClientTest {

    @Autowired
    ComputeClient computeClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void feign() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);

        List<ServiceInstance> ins = discoveryClient.getInstances("compute-service");
        System.out.println(ins.size());
        Integer result = computeClient.add(1, 2);
        System.out.println("get add:" + result);

        TimeUnit.MILLISECONDS.sleep(2000);

        result = computeClient.add(1, 2);
        System.out.println("get add:" + result);

    }

}
