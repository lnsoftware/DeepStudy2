

  <dependency>
            <groupId>com.zhiyin.frame</groupId>
            <artifactId>spring-boot-starter-reporter</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
        
        
reporter:
  metrics:
    # report to graphite
    types: elasticsearch2,console
    # report every 30 seconds
    interval: 1
    timeUnit: seconds
    es:
      hosts: 10.6.107.40:9200        