
### RemoteTokenServices构造

```
 @Bean
    public RemoteTokenServices remoteTokenServices( ) {
        final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(env.getProperty("check_token_url"));
        remoteTokenServices.setClientId( env.getProperty("client_id") );
        remoteTokenServices.setClientSecret( env.getProperty("client_secret") );
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
        return remoteTokenServices;
    }
```

### 远程调用

org.springframework.security.oauth2.provider.token.RemoteTokenServices.loadAuthentication

loadAuthentication 使用通过http调用远程服务

