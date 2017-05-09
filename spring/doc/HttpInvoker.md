

### 服务端


### 客户端

```
 <bean id="remoteService" class="org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean">  
        <property name="serviceUrl" value="http://localhost:8080/httpinvokertest/remoting/hit"/>  
        <property name="serviceInterface" value="org.tarena.note.service.HttpInvokerTest1"/>  
    </bean>  
```

HttpInvokerProxyFactoryBean

org.springframework.remoting.httpinvoker.HttpInvokerClientInterceptor.invoke



http://blog.csdn.net/u012291108/article/details/52938830