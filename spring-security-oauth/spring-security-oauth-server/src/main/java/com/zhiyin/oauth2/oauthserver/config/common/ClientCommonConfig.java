package com.zhiyin.oauth2.oauthserver.config.common;

import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;

/**
 * Created by hg on 2016/6/23.
 */
public class ClientCommonConfig {


    public static  void getClient(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
//                .jdbc( oauthDataSource )
                .inMemory()

                .withClient("admin")
                .secret("admin")
                .authorizedGrantTypes("password","authorization_code", "refresh_token")
                .scopes("read","write","foo","bar")
                .accessTokenValiditySeconds(3600) // 1 hour
                .refreshTokenValiditySeconds(2592000) // 30 days

                .and()
                .withClient("appclient")
                .secret("app123456")
                .authorizedGrantTypes("password","authorization_code", "refresh_token")
                .scopes("read","write","app")
                .accessTokenValiditySeconds(3600) // 1 hour
                .refreshTokenValiditySeconds(2592000) // 30 days

                .and()
                .withClient("mobile-client")
                .secret("mobile")
                .authorizedGrantTypes("password","authorization_code", "refresh_token")
                .scopes("read","write","app")
                .accessTokenValiditySeconds(3600*24*30) // 1 hour
                .refreshTokenValiditySeconds(2592000) // 30 days


                .and()
                .withClient("fooClientIdPassword")
                .secret("secret")
                .authorizedGrantTypes("password","authorization_code", "refresh_token")
                .scopes("foo","read","write")
                .accessTokenValiditySeconds(3600) // 1 hour
                .refreshTokenValiditySeconds(2592000) // 30 days

                .and()
                .withClient("barClientIdPassword")
                .secret("secret")
                .authorizedGrantTypes("password","authorization_code", "refresh_token")
                .scopes("bar","read","write")
                .accessTokenValiditySeconds(3600) // 1 hour
                .refreshTokenValiditySeconds(2592000) // 30 days
        ;

    }
}
