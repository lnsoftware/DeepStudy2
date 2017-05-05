package com.zhiyin.nsio.client.config;

import com.alibaba.fastjson.JSON;
import com.zhiyin.nsio.entity.PointC2s;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.URISyntaxException;

/**
 * Created by hg on 2016/5/16.
 */
@Slf4j
@Configuration
public class ClientConfig {

    @Value("${wss.server.url}")
    private String url;

    @Bean
    public Socket client() throws URISyntaxException {
        final Socket socket = IO.socket(url);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                System.out.println("connect");
                socket.emit("foo", "hi");
                socket.emit("addrid", JSON.toJSONString(new PointC2s(12.2D, 33D)));
//                socket.disconnect();
            }
        }).on("addrid", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                log.info("rec server response:{}", JSON.toJSONString(args));
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                log.error("disconnect to server.");
            }

        });

        socket.connect();

        log.info("program main end.");
        return socket;
    }

}

