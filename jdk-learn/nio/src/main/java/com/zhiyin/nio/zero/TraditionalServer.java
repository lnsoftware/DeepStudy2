package com.zhiyin.nio.zero;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TraditionalServer {

    public static void main(String args[]) {

        int port = 2000;
        ServerSocket server_socket;
        DataInputStream input;

        try {

            server_socket = new ServerSocket(port);
            System.out.println("Server waiting for client on port " +
                    server_socket.getLocalPort());

            while (true) {
                Socket socket = server_socket.accept();
                System.out.println("New connection accepted " +
                        socket.getInetAddress() +
                        ":" + socket.getPort());
                input = new DataInputStream(socket.getInputStream());
                try {
                    byte[] byteArray = new byte[4096];
                    while (true) {
                        int nread = input.read(byteArray, 0, 4096);
                        if (0 == nread)
                            break;
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
                try {
                    socket.close();
                    System.out.println("Connection closed by client");
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}