package org.elasticsearch.metrics;

import com.codahale.metrics.Clock;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.zhiyin.frame.metrics.JsonMetrics;
import com.zhiyin.frame.metrics.MetricsElasticsearchModule;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by hg on 2017/4/21.
 */
public class JSONTest {

    public static void main(String[] args) throws JsonProcessingException {


        Gauge<Integer> intGauge = new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return 1234;
            }
        };

        JsonMetrics.JsonGauge gauge = new JsonMetrics.JsonGauge(" helloWorldGroup.helloWorldKey.countFailure", new Date().getTime(), intGauge);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.CLOSE_CLOSEABLE, false);
        objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT, false);
        objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        objectMapper.registerModule(new AfterburnerModule());
            objectMapper.registerModule(new MetricsElasticsearchModule(TimeUnit.SECONDS, TimeUnit.MILLISECONDS, "@timestamp", null));

        String str = objectMapper.writer().writeValueAsString(gauge);
        System.out.println(str);
    }

}
