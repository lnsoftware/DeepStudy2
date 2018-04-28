

### 删除已经退出的容器

docker ps -aq -f status=exited | xargs docker rm -f

docker rm $(docker ps -a -q)



### 清理镜像
https://www.cnblogs.com/q4486233/p/6482711.html


想要删除untagged images，也就是那些id为<None>的image的话可以用
```
docker rmi $(docker images | grep "^<none>" | awk "{print $3}")
```


### 登录容器

 docker attach 44fc0f0582d9  
 docker exec -it 775c7c9ee1e1 /bin/bash  

 ### 镜像删除

1、解决问题，image has dependent child images
docker rmi -f test:0.91


docker run


docker run -v "$(pwd):/root" ubuntu:16:04  /bin/bash


docker run -i -t -v `pwd`:/root ubuntu:16.04 /bin/bash


docker build -f Dockerfile -t huiwq1990/linux-study .