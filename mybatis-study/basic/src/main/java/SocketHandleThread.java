import java.io.IOException;
import java.net.Socket;

/**
 * Created by lpmoon on 16-5-19.
 */
public class SocketHandleThread implements Runnable {
    private Socket socket;

    public SocketHandleThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        while (true) {
            byte[] bytes = new byte[1024];
            try {
                int count = socket.getInputStream().read(bytes);
                System.out.println(count);
                System.out.println(new String(bytes, 0, count));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}