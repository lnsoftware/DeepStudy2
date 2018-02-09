#FROM openjdk:8-jdk-alpine
FROM java:8
VOLUME /tmp
# 这个参数mvn里面设置的
ARG JAR_FILE
ADD ${JAR_FILE} /opt/app.jar
EXPOSE 8180
EXPOSE 5005

# suspend=n jvm直接启动
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n","/opt/app.jar"]
# suspend=y debugger连接后jvm才启动
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n","/opt/app.jar"]