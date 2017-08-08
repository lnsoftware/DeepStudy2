package com.zhiyin.thrift.bootexample.thrift;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhiyin.thrift.bootexample.Customer;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ThriftController {

    @Autowired
    ConsumerServiceThriftClient consumerServiceThriftClient;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "/test_thrift", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test(@RequestParam(value = "compact", required = false, defaultValue = "false") boolean compact) throws TException, JsonProcessingException {

        Customer list = consumerServiceThriftClient.queryById(1);


        String jsonString = objectMapper.writeValueAsString(list);

        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }
}

