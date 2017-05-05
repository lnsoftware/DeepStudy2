package com.hg.frame.eureka;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.config.server.jpa.ConfigInfo;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * https://spring.io/guides/tutorials/bookmarks/
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleDataJpaApplicationTests {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    static ConfigInfo configInfo() {
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setApplication("demo-app");
        configInfo.setProfile("dev");
        configInfo.setKey("demo-key");
        configInfo.setValue("val");
        return configInfo;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(configInfo()));
    }

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testHome() throws Exception {
        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    @Test
    public void testJmx() throws Exception {
        this.mvc.perform(post("/add").content(json(configInfo())).contentType(contentType)).andExpect(status().isOk())
                .andExpect(content().string("OK"));

        this.mvc.perform(get("/list/" + configInfo().getApplication() + "/" + configInfo().getProfile())).andExpect(status().isOk())
                .andExpect(jsonPath("$.propertySources[0].source."+configInfo().getKey(), is(configInfo().getValue())));

//		assertEquals(configInfo().getValue() , environment.getPropertySources().get(0).getSource().get( configInfo().getKey() ));
    }

    protected String json(Object o) throws IOException {
        return JSON.toJSONString(o);
//		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
//		this.mappingJackson2HttpMessageConverter.write(
//				o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
//		return mockHttpOutputMessage.getBodyAsString();
    }

}