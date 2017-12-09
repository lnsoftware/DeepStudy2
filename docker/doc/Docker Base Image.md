
## 1. 使用 Tar 创建 Docker 基本映像

### 思想是

获取Linux系统的基本文件系统，然后打包压缩。最后使用docker import命令，将系统编程docker image。

#### debootstrap说明
debootstrap是debian/ubuntu下的一个工具，用来构建一套基本的系统(根文件系统)。生成的目录符合Linux文件系统标准(FHS)，即包含了/boot、/etc、/bin、/usr等等目录，但它比发行版本的Linux体积小很多，当然功能也没那么强大，因此，只能说是“基本的系统”。

### 制作Demo

例如创建Ubuntu的基本镜像，登录Ubuntu系统。

```
sudo apt-get install debootstrap
# 抽取文件
sudo debootstrap trusty trusty
# 压缩文件
sudo tar zcvf trusty.tar.gz trusty
# 在宿主机执行
sudo docker import - trusty
```

![img](http://owq4ci2fg.bkt.clouddn.com/17-12-9/22412459.jpg)

![](http://owq4ci2fg.bkt.clouddn.com/17-12-9/3898807.jpg)


http://blog.csdn.net/kongxx/article/details/52618517

## 2. Vagrant创建

https://github.com/William-Yeh/docker-busybox-sh

这个过程同第一个类似

使用Vagrant启动一个Ubuntu系统（方便mac系统实验），然后构建busybox系统。最后结合scratch镜像制作。

## 3. 使用scratch

参见官网

### 参考
http://www.latelee.org/using-gnu-linux/ubuntu-debootstrap.html
https://linux.cn/article-5427-1.html
https://docs.docker.com/engine/userguide/eng-image/baseimages/#create-a-simple-parent-image-using-scratch
