
FROM java:8
VOLUME /tmp
# 这个参数mvn里面设置的
#ARG JAR_FILE
#ADD ${JAR_FILE} /opt/app.jar

ADD target/devtools-demo-0.0.1-SNAPSHOT.jar /opt/app.jar

EXPOSE 8180

# suspend=n jvm直接启动
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n","/opt/app.jar"]

#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n","/opt/app.jar"]
# suspend=y debugger连接后jvm才启动
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=y","/opt/app.jar"]

CMD java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n,address=5005 -Djava.net.preferIPv4Stack=true -jar /opt/app.jar