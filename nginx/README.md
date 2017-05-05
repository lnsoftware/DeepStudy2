
 
## build dockerfile

sudo docker build -t="huiwq1990/openresty" .

## 进入工程目录，运行容器

docker run -t -i -p 8080:8080 -v `pwd`:/workspace -w=/workspace huiwq1990/openresty 
启动docker时，把当前目录映射到/workspace，并设置当前工作目录为它。

## container introduce

在dockerfile文件里，我们nginx的启动命令为：

```
CMD /usr/local/openresty/nginx/sbin/nginx -p `pwd` -c conf/nginx.conf
```

nginx启动时设置的启动文件为当前目录下conf/nginx.conf文件，所以我们工程目录必须有个这个文件。



Dockerfile参考
https://github.com/torhve/openresty-docker


