package com;

import com.netflix.zuul.context.RequestContext;
import com.zhiyin.gateway.GatewayApplication;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GatewayApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class SimpleZuulServerApplicationTests {

    //	public String url = ""
    public String onlineHello = "/user-api/hello/admin";
    public String grayHello = "/user-api-api/hello/admin-gray";
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
        assertNotNull(getRoute("/user-api/**"));
        assertNotNull(getRoute("/user-api-gray/**"));
    }

    @Test
    public void getOnSelf() {
        ResponseEntity<String> result = new TestRestTemplate().exchange(
                "http://localhost:" + this.port + onlineHello, HttpMethod.GET,
                new HttpEntity<Void>((Void) null), String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("hello:admin", result.getBody());
    }

    @Test
    public void getOnSelfViaFilter() {
        ResponseEntity<String> result = new TestRestTemplate().exchange(
                "http://localhost:" + this.port + "/testing123/1", HttpMethod.GET,
                new HttpEntity<Void>((Void) null), String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
