package com.zhiyin.apm.reporter.boot;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by hg on 2016/10/31.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {MetricsReporterConfig.class, MetricsStarterConfig.class})
public @interface EnableReporterAutoConfiguration {

}