package com.zhiyin.eureka.instrument.web.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@ConditionalOnProperty(value = "spring.sleuth.web.client.enabled", matchIfMissing = true)
@ConditionalOnClass(RestTemplate.class)
@EnableConfigurationProperties(ServerInvokerProperty.class)
public class ServerMarkWebClientAutoConfiguration {

    @Autowired
    private ServerInvokerProperty serverInvokerProperty ;

    @Bean
    @ConditionalOnMissingBean
    public ServerMarkRestTemplateInterceptor traceRestTemplateInterceptor() {
        return new ServerMarkRestTemplateInterceptor( serverInvokerProperty.getMarks() );
    }

    // 添加拦截器
    @Configuration
    protected static class MarkMapInterceptorConfiguration {

        @Autowired(required = false)
        private Collection<RestTemplate> restTemplates;

        @Autowired
        private ServerMarkRestTemplateInterceptor serverMarkRestTemplateInterceptor;

        @PostConstruct
        public void init() {
            if (this.restTemplates != null) {
                for (RestTemplate restTemplate : this.restTemplates) {
                    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>(
                            restTemplate.getInterceptors());
                    interceptors.add(this.serverMarkRestTemplateInterceptor);
                    restTemplate.setInterceptors(interceptors);
                }
            }
        }

    }
}
