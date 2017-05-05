package com.hg.oauth2.resserver.conf.oauth.remote;

import com.zhiyin.oauth.core.ZyAccessTokenConverter;
import com.zhiyin.oauth.core.ZyUserAuthenticationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

@PropertySource({ "classpath:remote_token_service.properties" })
@Configuration
@EnableWebSecurity
class ResourceConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public AccessTokenConverter accessTokenConverter() {
//        return new DefaultAccessTokenConverter();
        DefaultAccessTokenConverter accessTokenConverter = new ZyAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter());
        return accessTokenConverter;
    }

    @Bean
    public UserAuthenticationConverter userAuthenticationConverter(){
        return new ZyUserAuthenticationConverter();
    }


    @Autowired
    private Environment env;

//    @Bean
//    public RemoteTokenServices remoteTokenServices(final @Value("${auth.server.url}") String checkTokenUrl,
//                                                   final @Value("${auth.server.clientId}") String clientId,
//                                                   final @Value("${auth.server.clientsecret}") String clientSecret) {
//        final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setCheckTokenEndpointUrl(checkTokenUrl);
//        remoteTokenServices.setClientId(clientId);
//        remoteTokenServices.setClientSecret(clientSecret);
//        remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
//        return remoteTokenServices;
//    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        authenticationManager.setTokenServices(remoteTokenServices());
        return authenticationManager;
    }


    @Bean
    public RemoteTokenServices remoteTokenServices( ) {
        final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(env.getProperty("check_token_url"));
        remoteTokenServices.setClientId( env.getProperty("client_id") );
        remoteTokenServices.setClientSecret( env.getProperty("client_secret") );
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
        return remoteTokenServices;
    }
}