package com.zhiyin.nio.zero;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class TraditionalClient {

    public static void main(String[] args) {
        int port = 2000;
        String server = "localhost";
        Socket socket = null;
        DataOutputStream output = null;
        FileInputStream inputStream = null;
        int ERROR = 1;
        // connect to server
        try {
            socket = new Socket(server, port);
            System.out.println("Connected with server " +
                socket.getInetAddress() +
                ":" + socket.getPort());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(ERROR);
        }

        try {
            String fname = "C:/cnarea20160320.sql";
            inputStream = new FileInputStream(fname);
            output = new DataOutputStream(socket.getOutputStream());
            long start = System.currentTimeMillis();
            byte[] b = new byte[4096];
            long read, total = 0;
            while ((read = inputStream.read(b)) >= 0) {
                total = total + read;
                output.write(b);
            }
            System.out.println("bytes send--" + total + " and totaltime--" + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert output != null;
            output.close();
            socket.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

