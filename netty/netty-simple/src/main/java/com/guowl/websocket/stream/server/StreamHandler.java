package com.guowl.websocket.stream.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders.Names;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamHandler extends SimpleChannelInboundHandler<Object> {
	private static final Logger			logger			= LoggerFactory.getLogger(StreamHandler.class);

	private static final String			WEBSOCKET_PATH	= "/websocket";

	private WebSocketServerHandshaker	handshaker;

	private int							times			= 0;

	private int							dataLength		= 0;

	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof FullHttpRequest) {
			handleHttpRequest(ctx, (FullHttpRequest) msg);
		} else if (msg instanceof WebSocketFrame) {
			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
		// Handshake
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(getWebSocketLocation(req),
				null, false);
		handshaker = wsFactory.newHandshaker(req);
		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		} else {
			handshaker.handshake(ctx.channel(), req);
		}
	}

	private BufferedWriter	write	= null;

	private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
		// Check for closing frame
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
			return;
		}
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		
		// Send the uppercase string back.
		ByteBuf dataBuf = frame.content();
		byte[] bytes = new byte[dataBuf.readableBytes()];
		dataBuf.readBytes(bytes);
		
		String clientData = new String(bytes);
		// 代表新文件
		if (clientData.equals("9527")) {
			try {
				write = new BufferedWriter(new FileWriter("d:/copy/" + System.currentTimeMillis() + ".txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (write != null) {
			try {
				write.write(new String(clientData));
				write.write(System.lineSeparator());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		dataLength += clientData.length();

		if (frame.isFinalFragment()) {
			logger.info("dataLength : " + dataLength + " times : " + (++times));
			ctx.channel().write(new TextWebSocketFrame("server received times : " + times));
			try {
				write.flush();
				write.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	private static String getWebSocketLocation(FullHttpRequest req) {
		return "ws://" + req.headers().get(Names.HOST) + WEBSOCKET_PATH;
	}
}
