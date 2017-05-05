package com.zhiyin.nsio.server.handler;

import com.alibaba.fastjson.JSON;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.zhiyin.nsio.entity.PointC2s;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zhiyin.nsio.entity.ChatObject;


import java.util.UUID;

@Slf4j
@Component
public class ChatEventHandler {

    @Autowired
    private SocketIOServer server;

    @OnEvent(value = "chatevent")
    public void onEvent(SocketIOClient client, AckRequest request, ChatObject data) {
        log.info("rec chatevent:" + JSON.toJSONString(data));
        server.getBroadcastOperations().sendEvent("chatevent", data);
    }

    @OnEvent(value = "addrid")
    public void addridevent(SocketIOClient client, AckRequest request, String data) {
        log.info("rec addrid:" + data);
        PointC2s c2s = JSON.parseObject(data, PointC2s.class);
        String retStr = c2s.getLat() + "," + c2s.getLon();
        client.sendEvent("addrid", UUID.randomUUID());
    }

    @OnEvent(value = Socket.EVENT_DISCONNECT)
    public void disconnect(SocketIOClient client, AckRequest request, String data) {
        log.info("disconnect" + client.getSessionId());
    }

}
