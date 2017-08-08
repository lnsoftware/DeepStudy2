package com.zhiyin.thrift.bootexample;

import com.zhiyin.thrift.bootexample.service.CustomerService;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootThriftExampleApplication.class)
public class SpringBootThriftExampleApplicationTests {

    @Autowired
    protected TProtocolFactory protocolFactory;

    @Value("${local.server.port}")
    protected int port;

    protected CustomerService.Client client;

    @Before
    public void setUp() throws Exception {
        TTransport transport = new THttpClient("http://localhost:" + port + "/consumer");

        TProtocol protocol = protocolFactory.getProtocol(transport);

        client = new CustomerService.Client(protocol);
    }

    @Test
    public void testAdd() throws Exception {

        Customer query = client.queryById(1);

        Assert.assertEquals(1,query.getId());
    }
}
