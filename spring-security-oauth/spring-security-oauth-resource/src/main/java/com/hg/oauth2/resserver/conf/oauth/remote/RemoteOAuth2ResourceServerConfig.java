package com.hg.oauth2.resserver.conf.oauth.remote;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 通过远程方式验证token有效性
 */
@PropertySource({ "classpath:remote_token_service.properties" })
@Configuration
@EnableResourceServer
public class RemoteOAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "demo-res";

    @Autowired
    private Environment env;

	private TokenExtractor tokenExtractor = new BearerTokenExtractor();

	@Override
	public void configure(HttpSecurity http) throws Exception {
//		http.addFilterAfter(new OncePerRequestFilter() {
//			@Override
//			protected void doFilterInternal(HttpServletRequest request,
//					HttpServletResponse response, FilterChain filterChain)
//					throws ServletException, IOException {
//				// We don't want to allow access to a resource with no token so clear
//				// the security context in case it is actually an OAuth2Authentication
//				if (tokenExtractor.extract(request) == null) {
//					SecurityContextHolder.clearContext();
//				}
//				filterChain.doFilter(request, response);
//			}
//		}, AbstractPreAuthenticatedProcessingFilter.class);
//
		http.csrf().disable();
//		http.authorizeRequests().anyRequest().authenticated(); //确保我们应用中的所有请求都需要用户被认证
//
//		http.requestMatchers().antMatchers("/foos/**","/bars/**","/users/**");

        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/**").anonymous()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


//		http.authorizeRequests().an

    }

//    @Override
//    public void configure(final ResourceServerSecurityConfigurer config) {
//        config.resourceId(RESOURCE_ID);
//        config.tokenServices(remoteTokenServices());
//    }


}