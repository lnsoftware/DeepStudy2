package com.zhiyin.sotimeout;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by wangqinghui on 2018/4/25.
 */
public class DemoClient {

    public static void main(String[] args) {
        long t1 = 0;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 8080));
            // 设置so timeout 为2000毫秒
            socket.setSoTimeout(0);
            System.out.println("Connected.");
            InputStream in = socket.getInputStream();
            System.out.println("reading...");
            t1 = System.currentTimeMillis();
            in.read();
        } catch (IOException e) {
            long t2 = System.currentTimeMillis();
            System.out.println("read end, take -> " + (t2 - t1) + "ms");
            e.printStackTrace();
        } finally {
//            if (this.reader != null) {
//                try {
//                    this.reader.close();
//                } catch (IOException e) {
//                }
//            }e
        }
    }
}
