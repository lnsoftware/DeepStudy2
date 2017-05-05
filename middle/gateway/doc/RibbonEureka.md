
### 问题

通过ribbon访问服务时，服务提供方可能通过eureka提供也可能不。

```

zuul:
  routes:
    users:
      path: /myusers/**
      serviceId: users

ribbon:
  eureka:
    enabled: false

users:
  ribbon:
    listOfServers: example.com,google.com
	
```
完全禁止eureka,无法混合使用。

### 解决方法

```
@EnableHystrixDashboard
@EnableHystrix
@EnableAutoConfiguration
@EnableZuulProxy
@SpringCloudApplication
@RibbonClients({
        @RibbonClient(name = "local", configuration = LocalRibbonClientConfiguration.class)
})
public class GatewayApplication   {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}

class LocalRibbonClientConfiguration {
    @Bean
    public ServerList<Server> ribbonServerList() {
        DefaultClientConfigImpl config = new DefaultClientConfigImpl();
        config.loadProperties("local");
        ConfigurationBasedServerList serverList = new ConfigurationBasedServerList();
        serverList.initWithNiwsConfig(config);
        return serverList;

        // or
//         return new StaticServerList<>(new Server("ssssa", 80));
    }
}
```


https://github.com/spring-cloud/spring-cloud-netflix/issues/325
https://github.com/spring-cloud/spring-cloud-netflix/issues/564
https://github.com/spring-cloud/spring-cloud-netflix/issues/411

https://github.com/spring-cloud/spring-cloud-netflix/blob/v1.3.0.M1/docs/src/main/asciidoc/spring-cloud-netflix.adoc#spring-cloud-ribbon-without-eureka