package com.zhiyin.frame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by hg on 2017/2/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApp.class)
public class ApplicationTest {

    @Autowired
    private Environment env;

    @Test
    public void tst() {
        String provider = env.getProperty("third-provider");
        assertThat(provider).isEqualTo("hg");

        String appName = env.getProperty("spring.application.name");
        assertThat(appName).isEqualTo("TestThridEnv");

    }

}

@SpringBootApplication
class DemoApp {
}