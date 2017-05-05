package org.springframework.cloud.config.server.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.cloud.config.server.environment.NativeEnvironmentRepository;
import org.springframework.cloud.config.server.environment.SearchPathLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author hg
 */
@Configuration
@EnableJpaRepositories("org.springframework.cloud.config.server.jpa")
@EntityScan(basePackages = {"org.springframework.cloud.config.server.jpa"})
@ComponentScan(basePackages = {"org.springframework.cloud.config.server.jpa"})
public class JdbcEnvironmentRepositoryConfiguration {

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private ConfigInfoRepository configInfoRepository;

    @Bean
    public SearchPathLocator searchPathLocator() {
        return new NativeEnvironmentRepository(environment);
    }

    @Bean
    @Primary
    public EnvironmentRepository environmentRepository() {
        return new JdbcEnvironmentRepository(configInfoRepository);
    }

}
