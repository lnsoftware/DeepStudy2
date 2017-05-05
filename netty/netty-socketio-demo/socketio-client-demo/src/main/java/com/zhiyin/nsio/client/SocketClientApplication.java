package com.zhiyin.nsio.client;

import com.alibaba.fastjson.JSON;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.URISyntaxException;

/**
 * Created by hg on 2016/5/16.
 */
@Slf4j
@EnableScheduling
@SpringBootApplication
public class SocketClientApplication {

    public static void main(String[] args) throws URISyntaxException {
        SpringApplication.run(SocketClientApplication.class, args);
    }

}

