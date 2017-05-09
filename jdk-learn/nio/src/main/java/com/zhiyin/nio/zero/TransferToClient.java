package com.zhiyin.nio.zero;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class TransferToClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        TransferToClient sfc = new TransferToClient();
        sfc.testSendFile();
    }

    public void testSendFile() throws IOException, InterruptedException {
        String host = "localhost";
        int port = 9027;
        SocketAddress sad = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.connect(sad);
        sc.configureBlocking(true);
        String fName = "C:/text.txt";
        File file = new File(fName);
        long fSize = file.length();
        FileChannel fc = new FileInputStream(fName).getChannel();
        long start = System.currentTimeMillis();
        long currentSet = 0;
        currentSet = fc.transferTo(0, fSize, sc);
        System.out.println("total bytes transferred--" + currentSet + " and time taken in MS--" + (System.currentTimeMillis() - start));
        sc.close();
    }

}
