package com.zhiyin.gateway;

import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.loadbalancer.ConfigurationBasedServerList;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.netflix.zuul.monitoring.MonitoringHelper;
import com.zhiyin.gateway.extend.MineHttpClientRibbonCommandFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by hg on 16/5/24.
 */
@EnableHystrixDashboard
@EnableHystrix
@EnableAutoConfiguration
@EnableZuulProxy
@SpringCloudApplication
@RibbonClients({
        @RibbonClient(name = "local", configuration = LocalRibbonClientConfiguration.class)
})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Primary
    @Bean
    public RibbonCommandFactory<?> ribbonCommandFactory(
            SpringClientFactory clientFactory, ZuulProperties zuulProperties) {
        return new MineHttpClientRibbonCommandFactory(clientFactory, zuulProperties);
    }

    @Component
    public static class MyCommandLineRunner implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            MonitoringHelper.initMocks();

            FilterLoader.getInstance().setCompiler(new GroovyCompiler());
            try {
                FileUtils.forceMkdir(new File("/opt/data/gateway/filter"));
                FilterFileManager.setFilenameFilter(new GroovyFileFilter());
                FilterFileManager.init(10, "/opt/data/gateway/filter");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}

/*
class LocalServiceConfig {

    private String name = "local";

    @Bean
    @ConditionalOnMissingBean
    public IClientConfig ribbonClientConfig() {
        DefaultClientConfigImpl config = new DefaultClientConfigImpl();
        config.loadProperties(this.name);
        return config;
    }
    @Bean
    ServerList<Server> ribbonServerList(IClientConfig config) {
        ConfigurationBasedServerList serverList = new ConfigurationBasedServerList();
        serverList.initWithNiwsConfig(config);
        return serverList;
    }
}*/
class LocalRibbonClientConfiguration {
    @Bean
    public ServerList<Server> ribbonServerList() {
        DefaultClientConfigImpl config = new DefaultClientConfigImpl();
        config.loadProperties("local");
        ConfigurationBasedServerList serverList = new ConfigurationBasedServerList();
        serverList.initWithNiwsConfig(config);
        return serverList;

        // or
//         return new StaticServerList<>(new Server("ssssa", 80));
    }
}