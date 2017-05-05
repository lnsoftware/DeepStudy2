package com.zhiyin.frame.metrics;

import com.codahale.metrics.Gauge;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Created by hg on 2017/4/26.
 */
public class MetricsMapper {

    private static final MetricsMapper instance = new MetricsMapper();

    private ObjectMapper objectMapper ;

    //private constructor to avoid client applications to use constructor
    private MetricsMapper(){
        objectMapper = get();
    }

    public static MetricsMapper getInstance(){
        return instance;
    }

    public ObjectMapper getObjectMapper(){
        return objectMapper;
    }

    public ObjectMapper get(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.CLOSE_CLOSEABLE, false);
        objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT, false);
        objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        objectMapper.registerModule(new AfterburnerModule());
        objectMapper.registerModule(new MetricsElasticsearchModule(TimeUnit.SECONDS, TimeUnit.MILLISECONDS, "@timestamp", null));

        return objectMapper;
    }


}
