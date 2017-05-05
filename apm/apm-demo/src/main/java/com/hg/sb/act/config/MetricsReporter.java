package com.hg.sb.act.config;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableMetrics
public class MetricsReporter extends MetricsConfigurerAdapter {

    // reporting interval in milliseconds
    private static long interval = 5;

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

//        注册系统监控
//        metricRegistry.registerAll(new MemoryUsageGaugeSet());
//        metricRegistry.registerAll(new GarbageCollectorMetricSet());

//        Slf4jReporter.Builder builder = Slf4jReporter.forRegistry(metricRegistry);
//        Slf4jReporter reporter = builder.build();

//         控制台
        ConsoleReporter.Builder builder = ConsoleReporter.forRegistry(metricRegistry);
        ConsoleReporter reporter = builder.build();
        reporter.start(interval, TimeUnit.SECONDS);


//        ScheduledReporter reporter2 = InfluxdbReporter.forRegistry(metricRegistry)
//                .protocol(InfluxdbProtocols.http("101.200.185.137", 8086, "root", "root", "collectdb"))
//                .prefixedWith("hg")
//                .convertRatesTo(TimeUnit.SECONDS)
//                .convertDurationsTo(TimeUnit.MILLISECONDS)
//                .filter(MetricFilter.ALL)
//                .skipIdleMetrics(false)
//                .build();
//
//        reporter2.start(interval, TimeUnit.SECONDS);
    }


}