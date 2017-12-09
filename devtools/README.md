
mvn dockerfile:build

netstat -anp | grep 5005


docker run  -i -t -p 8080:8080 -p 5005:5005 huiwq1990/devtools-demo


java -Djava.security.egd=file:/dev/./urandom -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 test-tool.jar
