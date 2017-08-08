package com.zhiyin.thrift.bootexample;

import com.zhiyin.thrift.bootexample.Customer;
import com.zhiyin.thrift.bootexample.service.CustomerService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

@Service
public class ConsumererviceHandler implements CustomerService.Iface {


    @Override
    public Customer queryById(long id) throws TException {
        return new Customer().setId(id).setName("HG");
    }

    @Override
    public void create(Customer customer) throws TException {

    }
}
