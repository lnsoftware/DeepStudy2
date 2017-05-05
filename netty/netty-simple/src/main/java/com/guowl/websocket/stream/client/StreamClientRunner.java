/*
 * Copyright 2014 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.guowl.websocket.stream.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

import java.net.URI;

public final class StreamClientRunner {

	private final URI	uri;

	public StreamClientRunner(URI uri) {
		this.uri = uri;
	}

	public void run() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			// Connect with V13 (RFC 6455 aka HyBi-17). You can change it to V08
			// or V00.
			// If you change it to V00, ping is not supported and remember to
			// change
			// HttpResponseDecoder to WebSocketHttpResponseDecoder in the
			// pipeline.
			final StreamClientHandler handler = new StreamClientHandler(WebSocketClientHandshakerFactory.newHandshaker(
					uri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders()));

			final String protocol = uri.getScheme();
			int defaultPort;
			ChannelInitializer<SocketChannel> initializer;

			// Normal WebSocket
			if ("ws".equals(protocol)) {
				initializer = new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast("http-codec", new HttpClientCodec())
								.addLast("aggregator", new HttpObjectAggregator(8192))
								.addLast("ws-handler", handler);
					}
				};

				defaultPort = 80;
				// Secure WebSocket
			} else {
				throw new IllegalArgumentException("Unsupported protocol: " + protocol);
			}

			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(initializer);

			int port = uri.getPort();
			// If no port was specified, we'll try the default port:
			if (uri.getPort() == -1) {
				port = defaultPort;
			}

			Channel ch = b.connect(uri.getHost(), port).sync().channel();
            handler.handshakeFuture().sync();
            ByteBuf dataBuf = ch.alloc().buffer();
            dataBuf.writeBytes("start".getBytes());
            ch.writeAndFlush(new BinaryWebSocketFrame(false, 0, dataBuf));
            ch.writeAndFlush(new ContinuationWebSocketFrame(true, 0, "end"));
            Thread.sleep(1000 * 60 *60);
		} finally {
			group.shutdownGracefully();
		}
	}
}
