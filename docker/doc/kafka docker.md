

### wurstmeister

https://hub.docker.com/r/wurstmeister/kafka/

需要配置KAFKA_ADVERTISED_HOST_NAME，如果要kafka集群模式，不能配置为localhost。

启动时创建topic，在环境变量下添加配置

```
KAFKA_CREATE_TOPICS: "Topic1:1:3,Topic2:1:1:compact"
```


非compose环境下运行
http://www.jianshu.com/p/263164fdcac7
http://dockone.io/article/565

docker exec -it ${CONTAINER ID} /bin/bash
docker exec -it e51fa21fd0fc /bin/bash

e51fa21fd0fc


### ches

https://github.com/ches/docker-kafka

ZK_IP=$(docker inspect --format '{{ .NetworkSettings.IPAddress }}' zookeeper)

KAFKA_IP=$(docker inspect --format '{{ .NetworkSettings.IPAddress }}' docker_kafka_1 )



### 脚本使用

docker-compose -f docker/dc-kafka.yml up




### kafka 管理端

https://github.com/sheepkiller/kafka-manager-docker
