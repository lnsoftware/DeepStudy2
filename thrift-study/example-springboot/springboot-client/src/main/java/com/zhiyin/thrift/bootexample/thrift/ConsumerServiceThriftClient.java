package com.zhiyin.thrift.bootexample.thrift;

import com.zhiyin.thrift.bootexample.service.CustomerService;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsumerServiceThriftClient extends CustomerService.Client {

    public ConsumerServiceThriftClient(TProtocolFactory tProtocolFactory,
                                       @Value("${consumer.server.host}")
                                               String host,
                                       @Value("${consumer.server.port}")
                                               int port) throws TTransportException {
        super(tProtocolFactory.getProtocol(new THttpClient("http://" + host + ":" + port + "/consumer")));
    }

}
