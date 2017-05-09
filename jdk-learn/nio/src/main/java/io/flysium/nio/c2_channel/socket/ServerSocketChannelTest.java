package io.flysium.nio.c2_channel.socket;

import io.flysium.nio.ByteBufferUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * ServerSocketChannel示例
 *
 * @author Sven Augustus
 */
public class ServerSocketChannelTest {

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		ServerSocketChannel channel = null;
		try {
			// 打开 ServerSocketChannel
			channel = ServerSocketChannel.open();
			// 设置非阻塞模式，read的时候就不再阻塞
			channel.configureBlocking(false);
			// 将 ServerSocket 绑定到特定地址（IP 地址和端口号）
			channel.bind(new InetSocketAddress("127.0.0.1", 9595));

			while (true) {
				// 监听新进来的连接
				java.nio.channels.SocketChannel socketChannel = channel.accept();
				if (socketChannel == null) {
					// System.out.println("没有客户端连接");
					TimeUnit.SECONDS.sleep(1);
					continue;
				}
				System.out.println("准备读：");
				// 读取客户端发送的数据
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				buffer.clear();
				socketChannel.read(buffer);
				Object object = ByteBufferUtils.readObject(buffer);
				System.out.println(object);
				// 往客户端写数据
				String serializable = "您好，客户端" + socketChannel.getRemoteAddress();
				System.out.println("准备写：" + serializable);
				ByteBuffer byteBuffer = ByteBufferUtils.writeObject(serializable);
				socketChannel.write(byteBuffer);
			}
		} finally {
			//关闭 ServerSocketChannel
			if (channel != null) {
				channel.close();
			}
		}
	}


}
