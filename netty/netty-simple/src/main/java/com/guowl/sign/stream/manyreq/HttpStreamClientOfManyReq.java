package com.guowl.sign.stream.manyreq;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpVersion;

import java.net.URI;
import java.util.Map;

import com.guowl.sign.http.HttpClientReponseHandler;

public class HttpStreamClientOfManyReq {
	public void connect(URI uri) throws Exception {
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			System.out.println("start time : " + System.currentTimeMillis() / 1000);
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.option(ChannelOption.MAX_MESSAGES_PER_READ, Integer.MAX_VALUE);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new HttpRequestEncoder());
					ch.pipeline().addLast(new HttpResponseDecoder());
					ch.pipeline().addLast(new HttpClientReponseHandlerOfManyReq());
				}
			});

			ChannelFuture f = b.connect(uri.getHost(), uri.getPort()).sync();
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
}
