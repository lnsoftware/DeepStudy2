
http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html
 zuul:
  routes:
    users:
      path: /myusers/**
      url: http://example.com/users_service
These simple url-routes don’t get executed as a HystrixCommand nor can you loadbalance multiple URLs with Ribbon. To achieve this, specify a service-route and configure a Ribbon client for the serviceId (this currently requires disabling Eureka support in Ribbon: see above for more information), e.g.

application.yml
zuul:
  routes:
    users:
      path: /myusers/**
      serviceId: users

ribbon:
  eureka:
    enabled: false

users:
  ribbon:
    listOfServers: example.com,google.com