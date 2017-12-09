#FROM openjdk:8-jdk-alpine
FROM java:8
VOLUME /tmp
# 这个参数mvn里面设置的
ARG JAR_FILE
ADD ${JAR_FILE} /opt/app.jar
EXPOSE 8080
EXPOSE 5005
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005","/opt/app.jar"]
#CMD java -Djava.security.egd=file:/dev/./urandom -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 /opt/app.jar
