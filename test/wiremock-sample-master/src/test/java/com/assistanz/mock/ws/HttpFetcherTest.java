package com.assistanz.mock.ws;

import com.assistanz.mock.ws.HttpFetcher;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.client.HttpResponseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HttpFetcherTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(18089);

    private HttpFetcher instance;

    @Before
    public void init() {
        instance = new HttpFetcher("");
        stubFor(get(urlEqualTo("/hoge.txt")).willReturn(
                aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody("hoge")));
        stubFor(get(urlEqualTo("/500.txt")).willReturn(
                aResponse().withStatus(500).withHeader("Content-Type", "text/plain").withBody("hoge")));
        stubFor(get(urlEqualTo("/503.txt")).willReturn(
                aResponse().withStatus(503).withHeader("Content-Type", "text/plain").withBody("hoge")));
        
        stubFor(get(urlEqualTo("/")).willReturn(
                aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBodyFile("get.json")));
        stubFor(get(urlEqualTo("/v2")).willReturn(
                aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBodyFile("v2/get.json")));
    }

    @Test
    public void ok() throws Exception {
        String actual = instance.fetchAsString("http://localhost:18089/hoge.txt");
        String expected = "hoge";
        assertThat(actual, is(expected));
    }
    
    

    @Test(expected = HttpResponseException.class)
    public void notFound() throws Exception {
        instance.fetchAsString("http://localhost:18089/NOT_FOUND");
    }

    @Test(expected = HttpResponseException.class)
    public void internalServerError() throws Exception {
        instance.fetchAsString("http://localhost:18089/500.txt");
    }

    @Test(expected = HttpResponseException.class)
    public void serviceUnavailable() throws Exception {
        instance.fetchAsString("http://localhost:18089/503.txt");
    }
    
    @Test
    public void getVersions() throws Exception {
        HttpFetcher apiServer = new HttpFetcher("http://localhost:18089/");
        Versions versions = apiServer.getVersions();
        assertNotNull(versions);
        // Should have 2 versions
        assertEquals(2, versions.size());
        //First version should have id v2.0
        Version firstVersion = versions.get(0);
        assertEquals("v2.0", firstVersion.getId());
    }
    
    @Test
    public void getVersion() throws Exception {
        HttpFetcher apiServer = new HttpFetcher("http://localhost:18089/");
        Version version = apiServer.getVersion("v2");
        //Should not be null
        assertNotNull(version);
        //Sould contain version id v2.0
        assertEquals("v2.0", version.getId());
    }
}
