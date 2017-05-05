

Eureka A/B Testing

## server metadata

```
eureka:
  instance:
    metadataMap:
      version: 1.1
      variant: A
```

在resttemplate选择服务器的时候根据条件选择对应服务器。

https://jmnarloch.wordpress.com/2015/11/25/spring-cloud-ribbon-dynamic-routing/

http://blog.csdn.net/zhuchuangang/article/details/51202307



http://192.168.1.108:9000/api/DEMO-PROVIDER

http://192.168.1.108:9000/api/version



### 测试代码编写

测试Eureka注册的服务时，需要加上如下注解，否则无法获取注册的服务。

```
@WebIntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
```

http://stackoverflow.com/questions/30362656/integration-testing-spring-boot-service-using-eureka-services
