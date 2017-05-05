import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleZuulServerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class SimpleZuulServerApplicationTests {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private RouteLocator routes;

    private String getRoute(String path) {
        return this.routes.getMatchingRoute(path).getLocation();
    }

    @Before
    public void setTestRequestcontext() {
        RequestContext context = new RequestContext();
        RequestContext.testSetCurrentContext(context);
    }

    @Test
    public void bindRoute() {
        assertNotNull(getRoute("/testing123/**"));
    }

    @Test
    public void getOnSelf() {
        ResponseEntity<String> result = new TestRestTemplate().exchange(
                "http://localhost:" + this.port + "/", HttpMethod.GET,
                new HttpEntity<Void>((Void) null), String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Hello world", result.getBody());
    }

    @Test
    public void getOnSelfViaFilter() {
        ResponseEntity<String> result = new TestRestTemplate().exchange(
                "http://localhost:" + this.port + "/testing123/1", HttpMethod.GET,
                new HttpEntity<Void>((Void) null), String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}

// Don't use @SpringBootApplication because we don't want to component scan
@Configuration
@EnableAutoConfiguration
@RestController
@EnableZuulServer
class SimpleZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleZuulServerApplication.class, args);
    }

    @RequestMapping("/local")
    public String local() {
        return "Hello local";
    }

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    @Bean
    public ZuulFilter sampleFilter() {
        return new ZuulFilter() {
            @Override
            public String filterType() {
                return "pre";
            }

            @Override
            public boolean shouldFilter() {
                return true;
            }

            @Override
            public Object run() {
                return null;
            }

            @Override
            public int filterOrder() {
                return 0;
            }
        };
    }

}