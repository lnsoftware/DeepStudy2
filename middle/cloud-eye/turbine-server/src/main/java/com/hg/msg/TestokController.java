package com.hg.msg;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangqinghui on 2016/3/29.
 */
@RestController
public class TestokController {


    @RequestMapping(method = RequestMethod.GET, path = "/testok", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
