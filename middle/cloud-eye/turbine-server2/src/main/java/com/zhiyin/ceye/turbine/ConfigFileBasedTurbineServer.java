package com.zhiyin.ceye.turbine;

import com.netflix.turbine.init.TurbineInit;
import com.netflix.turbine.streaming.servlet.TurbineStreamServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@EnableHystrixDashboard
@SpringBootApplication
public class ConfigFileBasedTurbineServer extends SpringBootServletInitializer {

    @Bean
    public ServletListenerRegistrationBean turbineListener() {
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new TurbineListener());
        return bean;
    }

    static class TurbineListener implements ServletContextListener {
        private final Logger logger = LoggerFactory.getLogger(TurbineListener.class);

        @Override
        public void contextInitialized(ServletContextEvent servletContextEvent) {
            logger.info("Initialize Turbine!");
            TurbineInit.init();
        }

        @Override
        public void contextDestroyed(ServletContextEvent servletContextEvent) {
            logger.info("Shutdown Turbine!");
        }
    }

    @Bean
    public ServletRegistrationBean turbine() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new TurbineStreamServlet());
        bean.addUrlMappings("/turbine.stream");
        return bean;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ConfigFileBasedTurbineServer.class).web(true);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigFileBasedTurbineServer.class).web(true).run(args);
    }

}