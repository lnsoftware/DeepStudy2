
mvn dockerfile:build

netstat -anp | grep 5005


docker run  -i -t -p 8180:8180 -p 5005:5005 huiwq1990/devtools-demo


java -Djava.security.egd=file:/dev/./urandom -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 test-tool.jar



### devtool 学习

 docker build -f Dockerfile -t huiwq1990/devtools-demo .


docker run  -i -t -p 8180:8180 huiwq1990/devtools-demo


java -jar -Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n /opt/app.jar


docker build --tag=spring-tsers-dev .
docker run --publish=8080:8080 --rm=true --name=spring-tsers-dev spring-tsers-dev


mac 查看端口占用
lsof -Pni4 | grep 8000
 
 
 
 
 