package com.zhiyin.cloudeye.demo.controller;

import com.google.common.base.Optional;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.zhiyin.cloudeye.demo.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * just for test server.
 * Created by hg on 2016/6/15.
 */
@Slf4j
@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam(value = "name", required = false) String name) {
        name = Optional.fromNullable(name).or("default");
        return ResponseEntity.ok("hello " + name + ", I'm Demo." + " Random Num:" + helloService.random());
    }



    @RequestMapping(method = RequestMethod.GET, path = "/call_hystrix", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String greeting() {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("admin2");
        return helloWorldCommand.execute();
    }

    class HelloWorldCommand extends HystrixCommand<String> {

        final Logger logger = LoggerFactory.getLogger(HelloWorldCommand.class);

        private final String name;

        public HelloWorldCommand(String name) {
            super(HystrixCommandGroupKey.Factory.asKey("default"));
            this.name = name;
        }

        @Override
        protected String run() throws Exception {
            logger.info("HelloWorld Command Invoked");
            return "Hello " + name;
        }
    }


}
