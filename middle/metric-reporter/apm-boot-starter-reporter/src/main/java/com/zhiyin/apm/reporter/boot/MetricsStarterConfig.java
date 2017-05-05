package com.zhiyin.apm.reporter.boot;

import com.codahale.metrics.ScheduledReporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Slf4j
@Service
@ConditionalOnBean({ScheduledReporter.class, MetricsReporterConfig.class})
public class MetricsStarterConfig implements AutoCloseable {

    @Autowired
    MetricsReporterProperties metricsReporterProperties;

    @Autowired
    List<ScheduledReporter> scheduledReporters;

    @PostConstruct
    public void startReporter() {
//        log.info("start primary scheduledReporter.");
//        scheduledReporter.start(metricsReporterProperties.interval, metricsReporterProperties.timeUnit);

        for (ScheduledReporter reporter : scheduledReporters) {
            log.info("start reporter {}", reporter.toString());
            reporter.start(metricsReporterProperties.interval, metricsReporterProperties.timeUnit);
        }
    }

    @PreDestroy
    @Override
    public void close() throws Exception {
//        scheduledReporter.stop();
    }
}