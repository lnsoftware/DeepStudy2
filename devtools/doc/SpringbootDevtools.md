

### spring-dev-tools机制

底层原理：双类加载器机制
一个Base ClassLoader加载器，用于加载不会改变的第三方依赖的jar；
另一个Restart ClassLoader加载器，用于加载自己编写的类；
执行流程：当应用重启后，原先的Restart ClassLoader被丢掉、重新new一个Restart ClassLoader来加载这些修改过的东西，而Base ClassLoader却没有变化。这就是devtools重启速度快的原因。



http://www.baeldung.com/spring-boot-devtools

https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html