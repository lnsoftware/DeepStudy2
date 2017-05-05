springboot的turbine配置不支持Properties文件的配置方式（ConfigPropertyBasedDiscovery）
这个问题尚未解决
http://stackoverflow.com/questions/37677675/hystrix-turbine-is-not-working

但是可以通过turbine-web-1.0.0.war可以实现Properties配置方式

```
turbine.aggregator.clusterConfig=myCluster
turbine.instanceUrlSuffix.myCluster=/hystrix.stream
turbine.ConfigPropertyBasedDiscovery.myCluster.instances=localhost:8989

```

其中myCluster为配置的集群名称

为方便测试turbine聚合，需要就它与springboot进行集成，参考资料
https://greencrayon00.wordpress.com/tag/turbine/


工程需要config.properties文件进行配置，在springboot里面配置无效