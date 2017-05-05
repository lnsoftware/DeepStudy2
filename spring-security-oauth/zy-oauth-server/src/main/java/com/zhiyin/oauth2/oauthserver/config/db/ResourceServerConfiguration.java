package com.zhiyin.oauth2.oauthserver.config.db;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Created by hg on 2016/12/18.
 */
@Profile("db")
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration  extends
            ResourceServerConfigurerAdapter {

        private static final String RESOURCE_ID = "restservice";

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            // @formatter:off
            resources
                    .resourceId(RESOURCE_ID);
            // @formatter:on
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {

            http.csrf().disable();
            http.httpBasic().disable();

            // @formatter:off
            http
                    .authorizeRequests()
                    .antMatchers("/users").hasRole("ADMIN")
                    .antMatchers("/hello").authenticated();
            // @formatter:on

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        }



}
