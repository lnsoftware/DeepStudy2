package com.zhiyin.hystrix.module.addr.dao;


import com.zhiyin.hystrix.module.addr.entity.Address;
import org.apache.http.client.fluent.Request;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.IOException;

@Resource
public class AddressDao {
    private Logger logger = LoggerFactory.getLogger(AddressDao.class);

    public Address getAddress(String customerId) throws IOException {
        logger.info("Get address for customer {}", customerId);
        String response = Request.Get("http://localhost:9090/customer/" + customerId + "/address")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute()
                .returnContent()
                .asString();

        return new ObjectMapper().readValue(response, Address.class);
    }
}
