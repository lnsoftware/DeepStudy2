
## 配置

### 1.创建监控系统

```
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
   <groupId>de.codecentric</groupId>
   <artifactId>spring-boot-admin-server</artifactId>
   <version>1.3.2</version>
</dependency>
<dependency>
   <groupId>de.codecentric</groupId>
   <artifactId>spring-boot-admin-server-ui</artifactId>
   <version>1.3.2</version>
</dependency>
```


```
@SpringBootApplication
@EnableAdminServer
public class SpringbootmonitorApplication {

   public static void main(String[] args) {
      SpringApplication.run(SpringbootmonitorApplication.class, args);
   }
}
```

```
server.port=8080
spring.jackson.serialization.indent_output=true
endpoints.health.sensitive=false
```


### 监控项目

```
<dependency>
   <groupId>de.codecentric</groupId>
   <artifactId>spring-boot-admin-starter-client</artifactId>
   <version>1.3.2</version>
</dependency>
```

```
spring.application.name=testweb
spring.boot.admin.url=
```






## 遇到问题

### 1. 服务启动后，一直无法UP

```
management.port=8081
management.address=127.0.0.1
```
http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-monitoring.html

这个属性配置管理URL只能本机访问。


## 刷新周期

admin client定时上传信息到server，可以进行配置

## 参考

https://yq.aliyun.com/articles/2322

http://www.jianshu.com/p/e20a5f42a395

http://boot.ren/2015/08/26/spring-boot%E7%9B%91%E6%8E%A7/

http://codecentric.github.io/spring-boot-admin/1.3.3/#getting-started