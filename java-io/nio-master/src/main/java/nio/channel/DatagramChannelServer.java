package nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by yifan on 2017/4/15.
 */
public class DatagramChannelServer {

    final static int PORT = 9999;

    public static void main(String[] args) throws IOException{
        System.out.println("server starting and listen on port :" + PORT + " for incoming request...");
        DatagramChannel dcServer = DatagramChannel.open();
        dcServer.socket().bind(new InetSocketAddress(PORT));
        ByteBuffer symbol = ByteBuffer.allocate(4);
        ByteBuffer payload = ByteBuffer.allocate(16);
        while(true){
            payload.clear();
            symbol.clear();
            SocketAddress sa = dcServer.receive(symbol);
            if(sa == null)
                return;
            System.out.println("received request from " + sa);
            String stockSymbol = new String(symbol.array(),0,4);
            System.out.println("symbol: " + stockSymbol);
            if(stockSymbol.toUpperCase().equals("MSFT")){
                payload.putFloat(0,37.40f);
                payload.putFloat(4,37.22f);
                payload.putFloat(8, 37.48f);
                payload.putFloat(12, 37.41f);
            } else {
                payload.putFloat(0, 0.0f);
                payload.putFloat(4, 0.0f);
                payload.putFloat(8, 0.0f);
                payload.putFloat(12, 0.0f);
            }
            dcServer.send(payload,sa);
        }

    }
}
