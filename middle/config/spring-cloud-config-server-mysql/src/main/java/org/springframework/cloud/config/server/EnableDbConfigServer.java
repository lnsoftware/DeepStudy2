package org.springframework.cloud.config.server;

import org.springframework.cloud.config.server.jpa.JdbcEnvironmentRepositoryConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Import(JdbcEnvironmentRepositoryConfiguration.class)
@EnableConfigServer
public @interface EnableDbConfigServer {

}
