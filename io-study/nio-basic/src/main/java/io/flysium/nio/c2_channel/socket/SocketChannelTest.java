package io.flysium.nio.c2_channel.socket;

import io.flysium.nio.ByteBufferUtils;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * SocketChannel示例
 *
 * @author Sven Augustus
 */
@SuppressWarnings("unused")
public class SocketChannelTest {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        SocketChannel channel = null;
        try {
            // 打开SocketChannel
            channel = SocketChannel.open();
            // 设置非阻塞模式，read的时候就不再阻塞
            channel.configureBlocking(false);
            // tcp连接网络
            channel.connect(new InetSocketAddress("127.0.0.1", 9595));
            if (channel.finishConnect()) {// 连接服务器成功
                /**
                 * 往服务端写数据
                 */
                String serializable = "您好，ServerSocketChannel。";
                System.out.println("准备写：" + serializable);
                ByteBuffer byteBuffer = ByteBufferUtils.writeObject(serializable);
                channel.write(byteBuffer);
                System.out.println("准备读：");
                // 读取服务端发送的数据
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.clear();
                int numBytesRead = -1;
                while ((numBytesRead = channel.read(buffer)) != -1) {
                    if (numBytesRead == 0) {// 如果没有数据，则稍微等待一下
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    Object object = ByteBufferUtils.readObject(buffer);
                    System.out.println(object);
                }
            } else {
                System.out.println("连接失败，服务器拒绝服务");
                return;
            }
        } finally {
            // 关闭SocketChannel
            if (channel != null) {
                channel.close();
            }
        }
    }

}
