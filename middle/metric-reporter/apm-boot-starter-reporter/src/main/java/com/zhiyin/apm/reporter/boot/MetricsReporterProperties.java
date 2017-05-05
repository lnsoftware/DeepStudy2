package com.zhiyin.apm.reporter.boot;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Data
@ConfigurationProperties(prefix = "reporter.metrics")
public class MetricsReporterProperties implements InitializingBean, EnvironmentAware {


    String name;

    int interval = 1;
    TimeUnit timeUnit = TimeUnit.MINUTES;

    List<MetricsType> types;
    String prefix = "";
    String host = "localhost";
    int port = 2003;
    String logger = "metrics";

    private Map<String, Object> additionalFields = Maps.newConcurrentMap();

    private InetUtils inetUtils;
    private Environment environment;
    private EsConfig es;

    public MetricsReporterProperties() {

    }

    public MetricsReporterProperties(InetUtils inetUtils) {
        this.inetUtils = inetUtils;
    }

    @Override
    public void afterPropertiesSet() throws Exception {


        RelaxedPropertyResolver springPropertyResolver = new RelaxedPropertyResolver(this.environment, "spring.application.");
        String springAppName = springPropertyResolver.getProperty("name");
        if (StringUtils.hasText(springAppName)) {
            this.additionalFields.put("appName", springAppName);
        }

        this.additionalFields.put("serverIp", inetUtils.findFirstNonLoopbackHostInfo().getIpAddress());

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    @Data
    public static class EsConfig {
        private String hosts;

        private String index = "hystrix-metrics";

    }
}

