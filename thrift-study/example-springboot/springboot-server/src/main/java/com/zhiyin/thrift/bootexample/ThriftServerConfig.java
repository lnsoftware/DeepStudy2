package com.zhiyin.thrift.bootexample;

import com.zhiyin.thrift.bootexample.service.CustomerService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThriftServerConfig {

    @Bean
    public TProtocolFactory tProtocolFactory() {
        return new TBinaryProtocol.Factory();
    }

    @Bean
    public ServletRegistrationBean thriftBookServlet(TProtocolFactory protocolFactory, ConsumererviceHandler handler) {
        TServlet tServlet = new TServlet(new CustomerService.Processor<>(handler), protocolFactory);

        return new ServletRegistrationBean(tServlet, "/consumer");
    }
}
