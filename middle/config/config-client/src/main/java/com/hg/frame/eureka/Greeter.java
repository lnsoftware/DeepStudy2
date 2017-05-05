package com.hg.frame.eureka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RefreshScope
@Component
@RestController
public class Greeter {

//    @Value("${port}")
    int port;

    @RequestMapping(value = "/", produces = "application/json")
    public List<String> index() {
        List<String> env = Arrays.asList(
                "message.greeting is: ",
                "server.port is: " + port,
                "configuration.projectName is: "
        );
        return env;
    }
}
