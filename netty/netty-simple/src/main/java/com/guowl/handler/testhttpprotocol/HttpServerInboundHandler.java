package com.guowl.handler.testhttpprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guowl.utils.ByteBufToBytes;
import com.guowl.utils.ResponseBuilder;

// ���տͻ�����Ϣ����ӡ
public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {
	private static Logger	logger	= LoggerFactory.getLogger(HttpServerInboundHandler.class);
	private ByteBufToBytes	reader	= new ByteBufToBytes(100);
	private int				count	= 0;
	private ResponseBuilder	builder	= new ResponseBuilder();
	private int				length	= 0;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof HttpRequest) {
			HttpRequest request = (HttpRequest) msg;
			System.out.println("messageType:" + request.headers().get("messageType"));
			System.out.println("businessType:" + request.headers().get("businessType"));
			if (HttpHeaders.isContentLengthSet(request)) {
				length = (int) HttpHeaders.getContentLength(request);
			}
		}

		if (msg instanceof HttpContent) {
			HttpContent httpContent = (HttpContent) msg;
			ByteBuf content = httpContent.content();
			length -= content.readableBytes();

			if (reader.isEnd()) {
				reader = new ByteBufToBytes(100);
			}
			read(content);
			if (length == 0) {
				String resultStr = new String(reader.readFull());
				System.out.println("Client said:" + (++count) + "    " + resultStr.length());
				HttpResponse response = builder.buildResponse("over");
				ctx.writeAndFlush(response);
			}
		}
	}

	private void read(ByteBuf content) {
		if (content.readableBytes() == 0) {
			content.release();
			return;
		}

		reader.reading(content);
		if (reader.isEnd()) {
			String resultStr = new String(reader.readFull());
			System.out.println("Client said:" + (++count) + "    " + resultStr.length());

			reader = new ByteBufToBytes(100);
		}
		read(content);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		logger.info("HttpServerInboundHandler.channelReadComplete");
		ctx.flush();
	}

}
