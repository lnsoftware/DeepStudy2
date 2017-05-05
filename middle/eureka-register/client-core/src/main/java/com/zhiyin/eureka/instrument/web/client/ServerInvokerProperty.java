package com.zhiyin.eureka.instrument.web.client;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangqinghui on 2016/12/26.
 */
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ribbon.invoker")
public class ServerInvokerProperty {

    private Map<String,ServerInvokerConfig> marks ;//= new LinkedHashMap<>();

    @Data
    @NoArgsConstructor
    public static class ServerInvokerConfig {

        private String serverMark;

        private List<String> candidateServerMarks;

    }
}