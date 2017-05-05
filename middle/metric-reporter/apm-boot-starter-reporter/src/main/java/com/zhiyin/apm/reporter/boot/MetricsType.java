package com.zhiyin.apm.reporter.boot;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.metrics.ElasticsearchReporter;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Slf4j
public enum MetricsType {
    console() {
        @Override
        ScheduledReporter createReporter(MetricRegistry metricRegistry, MetricsReporterProperties config) {
            return ConsoleReporter
                    .forRegistry(metricRegistry)
                    .build();
        }
    },
    slf4j() {
        @Override
        ScheduledReporter createReporter(MetricRegistry metricRegistry, MetricsReporterProperties config) {
            return Slf4jReporter
                    .forRegistry(metricRegistry)
                    .prefixedWith(config.prefix)
                    .outputTo(LoggerFactory.getLogger(config.logger))
                    .build();
        }
    },
    graphite() {
        @Override
        ScheduledReporter createReporter(MetricRegistry metricRegistry, MetricsReporterProperties config) {
            return GraphiteReporter
                    .forRegistry(metricRegistry)
                    .prefixedWith(config.prefix)
                    .build(new Graphite(config.host, config.port));
        }
    },
    elasticsearch() {
        @Override
        ScheduledReporter createReporter(MetricRegistry metricRegistry, MetricsReporterProperties config) {
            ElasticsearchReporter reporter = null;
            try {
                reporter = ElasticsearchReporter
                        .forRegistry(metricRegistry)
                        .hosts(config.getEs().getHosts())
                        .index(config.getEs().getIndex())
                        .indexDateFormat("yyyy-MM-dd") //no date suffix
                        .additionalFields(config.getAdditionalFields())
                        .build();
            } catch (IOException e) {
                log.error("es start error.", e);
            }
            return reporter;
        }
    },

//    kafka() {
//        @Override
//        ScheduledReporter createReporter(MetricRegistry metricRegistry, MetricsReporterProperties config) {
//            ElasticsearchReporter reporter = null;
//            try {
//                reporter = ElasticsearchReporter
//                        .forRegistry(metricRegistry)
//                        .hosts(config.getEs().getHosts())
//                        .index(config.getEs().getIndex())
//                        .indexDateFormat("yyyy-MM-dd") //no date suffix
//                        .additionalFields(config.getAdditionalFields())
//                        .build();
//            } catch (IOException e) {
//                log.error("es start error.", e);
//            }
//            return reporter;
//        }
//    },

//    newrelic() {
//        @Override
//        ScheduledReporter createReporter(MetricRegistry metricRegistry, MetricsReporterProperties config) {
//
//            return NewRelicReporter.forRegistry(metricRegistry)
//                    .name(Optional.fromNullable(config.getName()).or("new relic reporter"))
//                    .filter(MetricFilter.ALL)
//                    .attributeFilter(new AllEnabledMetricAttributeFilter())
//                    .rateUnit(config.getTimeUnit())
//                    .durationUnit(TimeUnit.MILLISECONDS)
//                    .metricNamePrefix(config.getPrefix())
//                    .build();
//        }
//    },
//    influxdb() {
//        @Override
//        ScheduledReporter createReporter(MetricRegistry metricRegistry, MetricsReporterProperties config) {
//            return InfluxdbReporter.forRegistry(metricRegistry)
//                    .protocol(new HttpInfluxdbProtocol("101.200.185.137", 8086, "root", "root", "collectdb"))
//                    .prefixedWith(config.getPrefix())
//                    .convertRatesTo(TimeUnit.SECONDS)
//                    .convertDurationsTo(TimeUnit.MILLISECONDS)
//                    .filter(MetricFilter.ALL)
//                    .skipIdleMetrics(false)
//                    .build();
//        }
//    }
    ;


    abstract ScheduledReporter createReporter(MetricRegistry metricRegistry, MetricsReporterProperties config);

}
