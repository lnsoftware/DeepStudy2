package com.zhiyin.sotimeout;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangqinghui on 2018/4/25.
 */
public class DemoServer {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(8080);
//        服务端只接受socket但不发送任何数据给客户端
        Socket socket = serverSocket.accept();

        TimeUnit.SECONDS.sleep(1000);
    }
}
