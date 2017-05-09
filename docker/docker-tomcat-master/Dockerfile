FROM tomcat:8.0

MAINTAINER Ma Qian<maqian258@gmail.com>

COPY tomcat/conf/server.xml /usr/local/tomcat/conf/server.xml

RUN rm -rf /usr/local/tomcat/webapps/* \
  && rm -rf /etc/apt/sources.list /etc/apt/sources.list.d/* \
  && curl -sSL 'https://raw.githubusercontent.com/maqian/workarounds/master/debian/jessie/set-sources-cn' | sh \
  && curl -sSL 'https://raw.githubusercontent.com/maqian/workarounds/master/debian/jessie/set-timezone-cn' | sh \
  && curl -sSL 'https://raw.githubusercontent.com/maqian/workarounds/master/debian/jessie/set-locale-cn' | sh \
  && curl -sSL 'https://raw.githubusercontent.com/maqian/workarounds/master/docker/java-limit-memory-installer' | sh

ENV TIMEZONE=Asia/Shanghai TZ=Asia/Shanghai \
    LANG=zh_CN.UTF-8 LC_ALL=zh_CN.UTF-8 LANGUAGE=zh_CN.UTF-8:zh:en_US:en \
    JAVA_OPTS="-XX:+PrintVMOptions -XX:+PrintCommandLineFlags"
RUN ls /
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone