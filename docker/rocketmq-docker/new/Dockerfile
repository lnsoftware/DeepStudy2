FROM java:8

# Rocketmq version
ENV ROCKETMQ_VERSION 4.0.0-incubating

ENV BASE_DIR /opt
# Rocketmq home
ENV ROCKETMQ_HOME  ${BASE_DIR}/rocketmq-${ROCKETMQ_VERSION}

WORKDIR  ${ROCKETMQ_HOME}

RUN mkdir -p \
		${BASE_DIR}/logs \
		${BASE_DIR}/store

RUN curl https://dist.apache.org/repos/dist/release/incubator/rocketmq/${ROCKETMQ_VERSION}/rocketmq-all-${ROCKETMQ_VERSION}-bin-release.zip -o rocketmq.zip \
          && unzip rocketmq.zip \
          && mv apache-rocketmq-all/* . \
          && rmdir apache-rocketmq-all  \
          && rm rocketmq.zip

ADD *.sh ${ROCKETMQ_HOME}/bin/
ADD broker.properties ${ROCKETMQ_HOME}/bin/

RUN chmod +x bin/mqnamesrv
RUN chmod +x bin/mqbroker
RUN chmod +x bin/start.sh


#CMD sh ./start.sh
ENTRYPOINT ["./bin/start.sh"]


EXPOSE 9876 10909 10911
VOLUME /opt/logs \
		/opt/store

