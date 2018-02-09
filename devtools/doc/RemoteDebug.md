
### 服务端
org.springframework.boot.devtools.autoconfigure.RemoteDevToolsAutoConfiguration

tunnel服务器
org.springframework.boot.devtools.tunnel.server.HttpTunnelServer

获取jdi的调试端口
org.springframework.boot.devtools.tunnel.server.RemoteDebugPortProvider.getPort


### http tunnel调试

org.springframework.boot.devtools.remote.client.RemoteClientConfiguration.RemoteDebugTunnelClientConfiguration.remoteDebugTunnelClient

启动本地监听
org.springframework.boot.devtools.tunnel.client.TunnelClient.start

启动默认绑定IPV6的地址，需要配置IPv4，需要设置：
```java
-Djava.net.preferIPv4Stack=true
```

判断端口是否占用 org.springframework.boot.devtools.remote.client.LocalDebugPortAvailableCondition.isPortAvailable


### client监听文件变化，上传文件
org.springframework.boot.devtools.remote.client.ClassPathChangeUploader.performUpload




tunnel调试
http://localhost:8180/.~~spring-boot!~/debug