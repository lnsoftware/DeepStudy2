


docker build -f Dockerfile -t huiwq1990/java  .


docker build -f Dockerfile.centos7.openjdk -t huiwq1990/openjdk8:centos7 .
docker run -i -t huiwq1990/openjdk8:centos7 /bin/bash

java的docker镜像
https://github.com/nimmis/docker-java-centos