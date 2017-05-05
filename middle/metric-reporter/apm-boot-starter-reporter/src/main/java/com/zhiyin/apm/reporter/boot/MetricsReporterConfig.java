package com.zhiyin.apm.reporter.boot;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.codahalemetricspublisher.HystrixCodaHaleMetricsPublisher;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;

@Slf4j
@Configuration
@EnableConfigurationProperties
public class MetricsReporterConfig {

    @Autowired
    private Environment environment;


    @Value("${spring.application.name}")
    private String appName;

    public static List<MetricsType> defaultType() {
        List<MetricsType> types = Lists.newArrayList();
        types.add(MetricsType.slf4j);
        return types;
    }

    // 自定义 MetricRegistry
    @Bean
    @ConditionalOnMissingBean
    public MetricRegistry metricRegistry() {
        final MetricRegistry metricRegistry = new MetricRegistry();

//        metricRegistry.register("jvm.gc",new GarbageCollectorMetricSet());
//        metricRegistry.register("jvm.mem",new MemoryUsageGaugeSet());
//        metricRegistry.register("jvm.thread-states",new ThreadStatesGaugeSet());
        return metricRegistry;
    }


    @Bean
    @ConditionalOnMissingBean
    public InetUtilsProperties inetUtilsProperties() {
        return new InetUtilsProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public InetUtils inetUtils(InetUtilsProperties properties) {
        return new InetUtils(properties);
    }


    @Bean
    @ConditionalOnMissingBean
    public MetricsReporterProperties metricsReporterProperties(InetUtils inetUtils) {
        final MetricsReporterProperties metricsReporterProperties = new MetricsReporterProperties(inetUtils);
        return metricsReporterProperties;
    }


    @Bean
    List<ScheduledReporter> scheduledReporters(MetricRegistry metricRegistry, MetricsReporterProperties metricsReporterProperties) {

        List<MetricsType> types = metricsReporterProperties.getTypes();
        List<ScheduledReporter> reporters = Lists.newArrayList();
        if (types != null && types.size() > 0) {
            for (int i = 0; i < types.size(); i++) {
                log.info("create repoter {}", types.get(i));
                reporters.add(types.get(i).createReporter(metricRegistry, metricsReporterProperties));
            }
        }
        return reporters;
    }

    // 将hystrix数据集成到spring metrics中
    // hystrix官方提供的
    @Bean
    HystrixMetricsPublisher hystrixMetricsPublisher(MetricRegistry metricRegistry) {
        HystrixCodaHaleMetricsPublisher publisher = new HystrixCodaHaleMetricsPublisher(metricRegistry);
        HystrixPlugins.getInstance().registerMetricsPublisher(publisher);
        return publisher;
    }
}