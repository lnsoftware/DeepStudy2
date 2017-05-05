package com.zhiyin.apm.reporter.boot;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApp.class, webEnvironment = NONE)
public class ReporterConfigTest {

    @Autowired
    private MetricsReporterProperties metricsReporterProperties;

    @Test
    public void catalogLoads() {
        Map<String, Object> fields = metricsReporterProperties.getAdditionalFields();
        assertThat(fields).isNotEmpty();
        assertThat(fields.size()).isEqualTo(2);
        assertThat(fields.get("appName")).isEqualTo("demo");
        log.info(JSON.toJSONString(fields));
    }

}

@EnableReporterAutoConfiguration
@SpringBootApplication
class DemoApp {

}