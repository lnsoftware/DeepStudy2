package com.zhiyin.nsio.client.config;

import com.alibaba.fastjson.JSON;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.kosinov.entity.PointC2s;

import java.util.Random;

@Slf4j
@Component
public class TimerSendTask {

    @Autowired
    private Socket socket;

    @Scheduled(cron = "0/5 * * * * ?")
    public void updateRedis() {
        Random random = new Random();
        PointC2s c2s = new PointC2s(random.nextDouble(), random.nextDouble());
        String str = JSON.toJSONString(c2s);
        socket.emit("addridevent", str);
        log.info("send loc task, info:{}", str);
    }
}