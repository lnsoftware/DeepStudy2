package com.guowl.websocket.stream.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 本例实现了WebSocket分片场景<br>
 * client端采用分片的方式把一个文件发送到server<br>
 * server端第一次收到frame后，在本机创建一个文件<br>
 * 然后把后续接收到的数据写入到文件中<br>
 * frame结束后，关闭文件，开始接收新文件<br>
 * 
 * client分片的机制是这样的：以TextWebSocketFrame为第一个帧，该帧的final为false<br>
 * 第二个帧为ContinueWebSocketFrame，该帧的final为false<br>
 * 后续陆续发送ContinueWebSocketFrame，直到文件读取完毕，最后的ContinueWebSocketFrame的final为true。<br>
 * 
 * 分片时，不能直接发送ContinueWebSocketFrame，ContinueWebSocketFrame不能作为第一帧<br>
 * 必须结合TextWebSocketFrame 或 BinaryWebSocketFrame 实现分片。
 * 
 */
public class StreamServer {

	private final int	port;

	public StreamServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new WebSocketServerInitializer());

			Channel ch = b.bind(port).sync().channel();
			System.out.println("Web socket server started at port " + port + '.');

			ch.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8080;
		}
		new StreamServer(port).run();
	}
}
