package com.zhiyin.hystrix.conf.hystrix;


import com.netflix.config.ConfigurationManager;
import com.netflix.config.jmx.ConfigJMXManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang.Validate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * Configures the hystrix instance that is used to secure remote calls with the circuit breaker pattern.
 */
@Slf4j
@Component
public class HystrixBootstrapService {

    /**
     * Prefix that all hystrix relevant properties should have.
     */
    public static final String HYSTRIX_PROPERTY_PREFIX = "hystrix.";

    @Autowired
    private ConfigurableEnvironment environment;


    public void bootstrapHystrix() throws IOException, ConfigurationException {
        log.info("Bootstraping Hystrix");
        AbstractConfiguration configuration = ConfigurationManager.getConfigInstance(); //don't remove this, it's needed for configuration
        try {
            ConfigJMXManager.registerConfigMbean(configuration);
        } catch (Exception e) {
            if ("InstanceAlreadyExistsException".equals(e.getMessage())) {
                log.warn(e.getMessage());
            } else {
                log.error("Unable to register with JMX", e);
            }
        }
        ConfigurationManager.loadProperties(extractHystrixProperties(environment.getPropertySources()));
    }

    /**
     * Extracts the string, not null, properties from the enumerable property sources.
     *
     * @param propertySources property sources to extract properties from, must not be null
     * @return extracted properties
     */
    protected Properties extractHystrixProperties(PropertySources propertySources) {
        Validate.notNull(propertySources);
        Properties hystrixProperties = new Properties();
        for (PropertySource<?> source : propertySources) {
            if (EnumerablePropertySource.class.isInstance(source)) {
                EnumerablePropertySource<?> enumerablePropertySource = (EnumerablePropertySource<?>) source;
                for (String propertyKey : enumerablePropertySource.getPropertyNames()) {
                    if (propertyKey.startsWith(HYSTRIX_PROPERTY_PREFIX)) {
                        Object propertyValue = source.getProperty(propertyKey);
                        if (propertyValue != null && propertyValue instanceof String) {
                            hystrixProperties.setProperty(propertyKey, (String) propertyValue);
                        }
                    }
                }
            }
        }
        return hystrixProperties;
    }

}
