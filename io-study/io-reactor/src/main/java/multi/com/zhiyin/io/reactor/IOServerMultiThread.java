package multi.com.zhiyin.io.reactor;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOServerMultiThread {
    private static final Logger LOGGER = LoggerFactory.getLogger(IOServerMultiThread.class);

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(2345));
        } catch (IOException ex) {
            LOGGER.error("Listen failed", ex);
            return;
        }
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        InputStream inputstream = socket.getInputStream();
//            LOGGER.info("Received message {}", IOUtils.toString(inputstream));
                        IOUtils.closeQuietly(inputstream);
                    } catch (IOException ex) {
                        LOGGER.error("Read message failed", ex);
                    }
                }).start();
            }
        } catch (IOException ex) {
            IOUtils.closeQuietly(serverSocket);
            LOGGER.error("Accept connection failed", ex);
        }
    }
}