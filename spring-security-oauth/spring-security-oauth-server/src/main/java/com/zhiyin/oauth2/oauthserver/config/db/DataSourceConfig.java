package com.zhiyin.oauth2.oauthserver.config.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Created by hg on 2016/4/19.
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfig {

    @Autowired
    Environment env;

    @Value("classpath*:oauth-schema.sql")
    private Resource schemaScript;

    @Qualifier("userInfoDataSource")
    @Bean(name="userInfoDataSource")
    public DataSource userInfoDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer( ) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource( oauthDataSource() );
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        return populator;
    }


    /**
     * oauth数据库源
     */
    @Primary
    @Bean(name="oauthDataSource")
    public DataSource oauthDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("oauth.jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("oauth.jdbc.url"));
        dataSource.setUsername(env.getProperty("oauth.jdbc.user"));
        dataSource.setPassword(env.getProperty("oauth.jdbc.pass"));
        return dataSource;
    }

}
