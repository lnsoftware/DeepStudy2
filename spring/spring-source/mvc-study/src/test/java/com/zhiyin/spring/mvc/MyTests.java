package com.zhiyin.spring.mvc;

import com.zhiyin.spring.mvc.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(DemoApplication.class)
public class MyTests {

//    @Autowired
//    private TestRestTemplate restTemplate;
@Autowired
private MockMvc mvc;

    @Test
    public void exampleTest() throws Exception {
        mvc.perform(get("/"));
//        String body = this.restTemplatetTemplate.getForObject("/", String.class);
//        assertThat(body).isEqualTo("hello");hello
    }

}