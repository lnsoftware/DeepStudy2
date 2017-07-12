import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lpmoon on 16-5-19.
 */
public class Server {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        try {
            ServerSocket ss = new ServerSocket(8888);
            while (true) {
                Socket s = ss.accept();
                pool.submit(new SocketHandleThread(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}