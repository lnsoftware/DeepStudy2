//package com.zhiyin.trace.demo;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.embedded.LocalServerPort;
//import org.springframework.boot.test.WebIntegrationTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.web.client.RestTemplate;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ApplicationTest {
//
//
//    @LocalServerPort
//    int randomPort;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void home() {
//        String body = this.restTemplate.getForObject("/", String.class);
//        assertThat(body).isEqualTo("Hello World");
//    }
//
//    @Test
//    public void remoteHome() {
//        String body = this.restTemplate.getForObject("/remoteHome/"+randomPort, String.class);
////        assertThat(body).isEqualTo("Hello World");
//    }
//
//
//
//
//
//
//}
