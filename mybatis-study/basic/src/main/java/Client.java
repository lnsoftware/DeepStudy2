import java.io.IOException;
import java.net.Socket;

/**
 * Created by lpmoon on 16-5-19.
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            socket.getOutputStream().write("test".getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}