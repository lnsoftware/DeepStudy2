package io.flysium.nio.c2_channel.socket;

import io.flysium.nio.ByteBufferUtils;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.TimeUnit;

/**
 * 网络UDP通道（DatagramChannel）测试 --作为服务端
 *
 * @author Sven Augustus
 */
public class DatagramChannelServerTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		DatagramChannel channel = null;
		try {
			//打开DatagramChannel
			channel = DatagramChannel.open();
			//非阻塞模式
			channel.configureBlocking(false);
			//将 UDP 绑定到特定地址（IP 地址和端口号），作为服务端监听端口
			channel.bind(new InetSocketAddress("127.0.0.1", 9898));

			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while (true) {
				buffer.clear();
				SocketAddress socketAddress = channel.receive(buffer);
				if (socketAddress == null) {
					// System.out.println("没有客户端连接");
					TimeUnit.MILLISECONDS.sleep(1);
					continue;
				}
				System.out.println("准备读："+ socketAddress);
				Serializable object = ByteBufferUtils.readObject(buffer);
				System.out.println(object);
				// 往客户端写数据
				String serializable = "您好，客户端" + socketAddress.toString();
				System.out.println("准备写：" + serializable);
				ByteBuffer byteBuffer = ByteBufferUtils.writeObject(serializable);
				channel.send(byteBuffer,socketAddress);
			}
		} finally {
			//关闭DatagramChannel
			if (channel != null) {
				channel.close();
			}
		}

	}

}
