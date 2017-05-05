//import com.alibaba.fastjson.JSON;
//import com.zhiyin.frame.api.DemoConsumerApplication;
//import com.zhiyin.frame.api.NotificationService;
//import com.zhiyin.frame.api.RemoteHelloService;
//import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
//import lombok.extern.slf4j.Slf4j;
//import org.assertj.core.api.StrictAssertions;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.boot.test.WebIntegrationTest;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.web.client.RestTemplate;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by wangqinghui on 2016/12/25.
// */
//@Slf4j
//@WebIntegrationTest
//@SpringApplicationConfiguration(classes = {DemoConsumerApplication.class})
//@RunWith(SpringJUnit4ClassRunner.class)
//public class EurekaClientTest {
//
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @Autowired
//    RemoteHelloService remoteHelloService;
//
//    @Autowired
//    protected RestTemplate restTemplate;
//
//    static String ProviderHelloUrl = "http://DEMO-PROVIDER/demo-provider/hello";
//
//
//    @Test
//    public void discoveryClient(){
//      log.info("all services:" + JSON.toJSONString(discoveryClient.getServices()));
//    }
//    @Test
//    public void remoteHelloCall(){
//        String hello  = remoteHelloService.hello();
//        assertThat(hello).isEqualTo("hello");
//        log.info("remote hello: {} " , hello );
//    }
//
//    @Test
//    public void restTemplateCall(){
//        ResponseEntity<String> response = restTemplate.getForEntity(ProviderHelloUrl , String.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertThat(response.getBody()).isEqualTo("hello");
//    }
//
//    @Test
//    public void restTemplateCallWithVersion(){
//        // given
//        RibbonFilterContextHolder.getCurrentContext().add("version", "GRAY");
//        ResponseEntity<String> response = restTemplate.getForEntity(ProviderHelloUrl , String.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertThat(response.getBody()).isEqualTo("hello");
//    }
//
//
//
//
//}
