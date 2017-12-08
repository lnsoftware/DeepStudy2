

### 网络配置

```
config.vm.network "public_network", bridge: "en0: Wi-Fi (AirPort)"
config.vm.boot_timeout = 20
```

boot_timeout 是链接超时设置（20 秒）， bridge 后面表示桥接的网络模式（WiFi 网络），如果不进行设置的话，每次启动虚拟机的时候，会进行选择网络模式：



http://blog.csdn.net/zc474235918/article/details/51039150