
### 前提

之前尝试在mac上使用Docker构建程序，一直不成功。
1）docker使用的内核是宿主机的内核，即使使用Ubuntu镜像，内核也是宿主机的。
2）执行命令`uname -r`一直返回moby，这是docker-machine的内核名称。原因同上。

insmod安装

apt-get install kmod

```
apt-get install build-essential linux-headers-`uname -r`
```

https://stackoverflow.com/questions/38229579/docker-container-lsmod-not-found