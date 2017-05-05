
记录Hystrix的历史数据，最方便的是把他们转化为Metrics数据。

### hystrix-codahale-metrics-publisher

hystrix-codahale-metrics-publisher与dropwizard metrics结合，即它会将hystrix的数据转化为dropwizard metrics格式。

官方提供的方式
http://www.nurkiewicz.com/2015/02/storing-months-of-historical-metrics.html
https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-codahale-metrics-publisher


```
<dependency>
    <groupId>com.netflix.hystrix</groupId>
    <artifactId>hystrix-codahale-metrics-publisher</artifactId>
    <version>1.1.2</version>
</dependency>
```


HystrixMetricsPublisher


### springboot hystrix metrics

```

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>
        
          <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-actuator</artifactId>
                </dependency>
```

开关
```
hystrix:
  metrics:
    enabled: true
```

原理：
org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerConfiguration.HystrixMetricsPollerConfiguration 会将hystrix数据进行记录，通过actuator