

## zuul timeout config

```
hystrix:
  command:
    sample-svc:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 15000
```



```
sample-svc:
  ribbon:
    ReadTimeout: 15000
```

https://www.javacodegeeks.com/2016/06/spring-cloud-zuul-support-configuring-timeouts.html

https://github.com/Netflix/ribbon/wiki/Programmers-Guide
