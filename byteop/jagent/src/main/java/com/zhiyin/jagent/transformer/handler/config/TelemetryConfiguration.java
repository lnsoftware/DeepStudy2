package com.zhiyin.jagent.transformer.handler.config;


import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class TelemetryConfiguration {

    private List<String> excludePackage = Lists.newArrayList();

    private List<String> includePackage = Lists.newArrayList();

    private String packageStrategy = "black";


    private List<String> handlers = Collections.emptyList();
    private SinkConfiguration sinks = new SinkConfiguration();


}
