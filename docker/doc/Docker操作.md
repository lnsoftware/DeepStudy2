

docker ps -aq -f status=exited | xargs docker rm -f


清理镜像
https://www.cnblogs.com/q4486233/p/6482711.html


想要删除untagged images，也就是那些id为<None>的image的话可以用
```
docker rmi $(docker images | grep "^<none>" | awk "{print $3}")
```
