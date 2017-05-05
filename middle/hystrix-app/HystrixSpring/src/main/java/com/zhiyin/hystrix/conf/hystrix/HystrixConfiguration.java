package com.zhiyin.hystrix.conf.hystrix;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:hystrix.properties")
class HystrixConfiguration {

    @Autowired
    private HystrixBootstrapService hystrixBootstrapService;

    /**
     * 暴露stream接口
     * to expose stream endpoint
     */
//    <servlet>
//    <servlet-name>HystrixMetricsStreamServlet</servlet-name>
//    <servlet-class>com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet</servlet-class>
//    </servlet>
//    <servlet-mapping>
//    <servlet-name>HystrixMetricsStreamServlet</servlet-name>
//    <url-pattern>/hystrix.stream</url-pattern>
//    </servlet-mapping>
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new HystrixMetricsStreamServlet(), "/hystrix.stream");
    }

    /**
     * to expose properties, @PropertySource is needed
     */
    @PostConstruct
    public void init() throws Exception {
        hystrixBootstrapService.bootstrapHystrix();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
