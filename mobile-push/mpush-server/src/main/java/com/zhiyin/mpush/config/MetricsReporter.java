package com.zhiyin.mpush.config;

import java.util.concurrent.TimeUnit;

import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
@EnableMetrics
public class MetricsReporter extends MetricsConfigurerAdapter {

    // reporting interval in milliseconds
    private static long interval = 100 ;

    @Autowired
    Environment environment;

//    @Value("${graphite.host:localhost}")
//    String graphiteHost;

//    @Bean
//    @ConditionalOnMissingBean(MetricRegistry.class)
//    public MetricRegistry metricRegistry() {
//        return new MetricRegistry();
//    }

//    @Bean
//    @ConditionalOnProperty("graphite.host")
//    public GraphiteReporter graphiteReporter() {
//        Graphite graphite = new Graphite(graphiteHost, 1337);
//        GraphiteReporter reporter = GraphiteReporter.forRegistry(metricRegistry()).build(graphite);
//        reporter.start(interval, TimeUnit.MILLISECONDS);
//
//        return reporter;
//    }

//    @Bean
//    public Slf4jReporter slf4jReporter() {
//        Slf4jReporter.Builder builder = Slf4jReporter.forRegistry(metricRegistry());
//        Slf4jReporter reporter = builder.build();
//        reporter.start(interval, TimeUnit.MILLISECONDS);
//        return reporter;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(Slf4jReporter.class)
//    public ConsoleReporter consoleReporter() {
//        ConsoleReporter.Builder builder = ConsoleReporter.forRegistry(metricRegistry());
//        ConsoleReporter reporter = builder.build();
//        reporter.start(interval, TimeUnit.MILLISECONDS);
//        return reporter;
//    }

//    @PostConstruct
//    public void registerMetrics() {
//        metricRegistry().registerAll(new MemoryUsageGaugeSet());
//        metricRegistry().registerAll(new GarbageCollectorMetricSet());
//    }


    @Override
    public void configureReporters(MetricRegistry metricRegistry) {

        metricRegistry.registerAll(new MemoryUsageGaugeSet());
        metricRegistry.registerAll(new GarbageCollectorMetricSet());

//        Slf4jReporter.Builder builder = Slf4jReporter.forRegistry(metricRegistry);
//        Slf4jReporter reporter = builder.build();
        ConsoleReporter.Builder builder = ConsoleReporter.forRegistry(metricRegistry);
        ConsoleReporter reporter = builder.build();
        reporter.start(interval, TimeUnit.SECONDS);
    }


}