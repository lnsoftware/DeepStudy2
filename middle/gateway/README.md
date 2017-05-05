


## zuul高可用性配置

http://stackoverflow.com/questions/38391040/how-do-i-make-spring-zuul-high-available


## zuul 限流配置

https://github.com/Netflix/zuul/issues/196

https://github.com/marcosbarbero/spring-cloud-starter-zuul-ratelimit

## zuul 处理post请求
http://stackoverflow.com/questions/35088498/how-to-get-response-body-in-zuul-post-filter
https://github.com/Netflix/zuul/issues/193

测试请求
使用zuul将hello进行route，并可以更改请求信息。
post localhost:8080/test/hello {"name":"sssss"}
