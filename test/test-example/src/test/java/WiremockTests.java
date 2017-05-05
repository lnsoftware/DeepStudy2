

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

public class WiremockTests {

    RestTemplate restTemplate;
    ResponseEntity response;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8089).httpsPort(8443));

    @Before
    public void setup() throws Exception {
        restTemplate = new RestTemplate();
        response = null;
    }

    @Test
    public void givenWireMockAdminEndpoint_whenGetWithoutParams_thenVerifyRequest() {

        RestTemplate restTemplate = new RestTemplate();

        response = restTemplate.getForEntity("http://localhost:8089/__admin", String.class);

//        assertThat("Verify Response Body", response.getBody().contains("mappings"));
        assertThat("Verify Status Code", response.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void givenWireMockEndpoint_whenGetWithoutParams_thenVerifyRequest() {
        stubFor(get(urlEqualTo("/api/resource/"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", TEXT_PLAIN_VALUE)
                        .withBody("test")));

        response = restTemplate.getForEntity("http://localhost:8089/api/resource/", String.class);

//        assertThat("Verify Response Body", response.getBody().contains("test"));
        assertThat("Verify Status Code", response.getStatusCode().equals(HttpStatus.OK));

        verify(getRequestedFor(urlMatching("/api/resource/.*")));
    }
}