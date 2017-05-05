package user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringCloudApplication
@EnableAutoConfiguration
@EnableCircuitBreaker
@RestController
@EnableZuulServer
public class UserApplication {

//		@RequestMapping("/local")
//		public String local() {
//			return "Hello local";
//		}

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);

    }

//    @Bean
//    public ZuulFilter sampleFilter() {
//        return new AccessLogFilter();
//    }

    @RequestMapping("/hello")
    public String home() {
        return "sssss";
    }
}

