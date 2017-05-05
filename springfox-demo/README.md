#simpleApp
##spring boot + mybatis �����С��Ӧ��
##swagger��ΪRESTFUL�ӿڵ��ĵ������Զ�����+���ܲ���




curl -X POST -vu clientapp:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=spring&username=roy&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"



http://localhost:8080/oauth/token?password=spring&username=roy&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp







## springfox 去掉默认状态码

```
 new Docket()
        //More config
        .useDefaultResponseMessages(false) //<-- this should be false
```


http://stackoverflow.com/questions/30047129/swagger-springfox-always-generates-some-response-messages-401-403-by-defa


## oauth

https://github.com/royclarkson/spring-rest-service-oauth