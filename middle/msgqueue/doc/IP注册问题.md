


http://blog.csdn.net/upcye/article/details/53888388
https://rocketmq.incubator.apache.org/docs/quick-start/

rocketmq不会优先使用loopback网卡，如果要指定注册IP，需要增加启动配置文件


org.apache.rocketmq.broker.BrokerStartup#createBrokerController
-c 参数指定配置文件，读取配置文件并将参数设置到brokerConfig。
