
DefaultTokenServices 提供了回收token的方法
在封装接口的时候会报：Can't Autowire DefaultTokenService 错误
解决方法：
@EnableAspectJAutoProxy(proxyTargetClass=true)

http://stackoverflow.com/questions/35261231/autowire-defaulttokenservices-failing-in-jenkins-build-but-working-perfectly-wh




