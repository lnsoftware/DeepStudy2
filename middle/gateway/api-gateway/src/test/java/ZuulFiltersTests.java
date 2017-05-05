import com.netflix.zuul.context.RequestContext;
import config.GlobalConfig;
import filters.FiltersApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FiltersApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ActiveProfiles("filters")
public class ZuulFiltersTests {


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
        assertNotNull(getRoute("/local/**"));
    }

    @Test
    public void getOnSelf() {
        ResponseEntity<String> result = new TestRestTemplate().exchange(
                "http://localhost:" + this.port + "/hello", HttpMethod.GET,
                new HttpEntity<Void>((Void) null), String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(GlobalConfig.HelloStr, result.getBody());
    }

    @Test
    public void getOnSelfViaFilter() {
        ResponseEntity<String> result = new TestRestTemplate().exchange(
                "http://localhost:" + this.port + "/local/hello", HttpMethod.GET,
                new HttpEntity<Void>((Void) null), String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(GlobalConfig.HelloStr, result.getBody());
    }
}
