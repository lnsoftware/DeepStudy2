
## 参考

jwt
https://www.javacodegeeks.com/2016/04/spring-oauth2-jwt-sample.html
oauth2分析
http://www.oschina.net/translate/oauth-2-developers-guide

基于jwt的oauthserver 与 resourcesserver 分离方案
https://github.com/spring-guides/tut-spring-security-and-angular-js/blob/master/oauth2%2FREADME.adoc

使用RemoteTokenServices 解决授权与资源分离
https://github.com/sharmaritesh/spring-angularjs-oauth2-sample

spring oauth服务器与resource server分离
https://github.com/eugenp/spring-security-oauth

spring oauth2 jdbc版
http://www.concretepage.com/spring/spring-security/spring-mvc-security-jdbc-authentication-example-with-custom-userdetailsservice-and-database-tables-using-java-configuration
http://www.baeldung.com/rest-api-spring-oauth2-angularjs

## Spring Security OAuth

### Relevant Articles: 
- [Spring REST API + OAuth2 + AngularJS](http://www.baeldung.com/rest-api-spring-oauth2-angularjs)

### Build the Project
```
mvn clean install
```

### Notes
- Make sure to run the project on port 8081
- Run 4 sub-modules simultaneously 
    - spring-security-oauth-server
    - spring-security-oauth-resource
    - spring-security-oauth-ui-implicit
    - spring-security-oauth-ui-password



测试

curl admin admin
http://localhost:8096/oauth-server/oauth/token?client_id=admin&client_secret=admin&grant_type=password&scope=foo read write bar&username=krishna&password=k123
