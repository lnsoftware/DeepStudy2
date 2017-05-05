
## newrelic使用

https://javaguru.fi/tags/newrelic


## newrelic agent使用



springboot中使用newrelic

https://dzone.com/articles/including-java-agent


## newrelic metrics

将metrics的数据发送到newrelic上面，原理是使用newrelic api开发自己的监控指标。
自定义metrics必须以Custom开头，程序需要运行10min，以便于数据传送到服务器。

https://github.com/palominolabs/metrics-new-relic

官网
https://docs.newrelic.com/docs/agents/manage-apm-agents/agent-metrics/custom-metrics#best_practices


启动配置
 -javaagent:/data/newrelic.jar