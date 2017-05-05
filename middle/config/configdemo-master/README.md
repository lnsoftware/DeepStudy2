##zookeeper
```shell
zkCli.sh -server=127.0.0.1:2181
create /config ""
create /config/demoapp ""
create /config/demoapp/msg helloworld
create /config/demoapp/first ""
create /config/demoapp/first/second tree
quit
```
##blog
- [SpringBoot使用zookeeper作为配置中心](http://segmentfault.com/a/1190000004356362)

##add refresh support
- ArchaiusZkConfig
使用Archaius Zookeeper,可以实现变量自动更新,不过是使用单例方式来获取的,而不是@Value更新这种
- CustomZkConfig
自己处理监听,自己实现类似Archaius Zookeeper的功能,干脆还是用人家的好了.