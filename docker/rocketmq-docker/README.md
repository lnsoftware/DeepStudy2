






sudo docker run --privileged -i -t rocketmq /bin/bash


## 启动mq
docker run -i -t -p 10911:10911 -p 10909:10909 -p 9876:9876 -p 10912:8080 rocketmq

--name rocketmq rocketmq /bin/bash




sh mqadmin clusterList -n 127.0.0.1:9876


### 其它启动办法



## 参考

https://github.com/dongeforever/rocketmq-docker
