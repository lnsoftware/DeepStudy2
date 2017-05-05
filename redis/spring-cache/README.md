涛哥讲redis cache
http://jinnianshilongnian.iteye.com/blog/2001040

应用加cache的经验
http://blog.csdn.net/defonds/article/details/48716161

性能测试
https://github.com/jayanderson/spring-data-redis-cache-performance-test

官方例子
https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples/spring-boot-sample-cache/README.adoc

## 生产所需

如果redis失败，禁用redis
http://stackoverflow.com/questions/29003786/how-to-disable-redis-caching-at-run-time-if-redis-connection-failed

记录redis失败
http://stackoverflow.com/questions/26021991/spring-redis-error-handle

取redis缓存失败，则不用缓存

使用CacheErrorHandler