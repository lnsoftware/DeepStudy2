package com.hg.sb.act.service.metric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.repository.MetricRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Iterator;

import static net.logstash.logback.marker.Markers.append;


/**
 * 定时将统计数据返回
 */

@Service
class MetricExporterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricExporterService.class);
    private final MetricRepository repository;

    @Autowired
    public MetricExporterService(MetricRepository repository) {
        this.repository = repository;
    }

    @Scheduled(initialDelay = 1000 * 10, fixedDelay = 1000 * 10)
    void exportMetrics() {
        LOGGER.debug("Reporting metrics");

        Iterator<Metric<?>> it = repository.findAll().iterator();
        while (it.hasNext()) {
            log(it.next());
        }
//
//        repository.findAll().forEach(this::log);
    }

    private void log(Metric<?> m) {
        LOGGER.info("Reporting metric {}={}", m.getName(), m.getValue());
        LOGGER.info(append("metric", m), "Reporting metric {}={}", m.getName(), m.getValue());
        repository.reset(m.getName());
    }

}