package com.zhiyin.trace.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;


@Service
public class LogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);

    @Autowired
    private Tracer tracer;

    public void log(String msg) {

        Span logServiceSpan = this.tracer.createSpan("pre-call-remote-api");

        try {
            LOGGER.info(msg);

        } finally {
            this.tracer.close(logServiceSpan);
        }
    }

    public void logPre(String msg) {

        Span logServiceSpan = this.tracer.createSpan("pre-call-remote-api");

        try {

            logServiceSpan.tag("hello","hello");
            LOGGER.info(msg);

        } finally {
            this.tracer.close(logServiceSpan);
        }
    }

    public void logEnd(String msg) {

        Span logServiceSpan = this.tracer.createSpan("after-call-remote-api");

        try {
            LOGGER.info(msg);

        } finally {
            this.tracer.close(logServiceSpan);
        }
    }
}