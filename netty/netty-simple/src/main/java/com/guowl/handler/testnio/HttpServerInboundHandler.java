package com.guowl.handler.testnio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import com.guowl.utils.ByteBufToBytes;
import com.guowl.utils.ResponseBuilder;

public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {
	private ByteBufToBytes	reader;
	private ResponseBuilder	builder	= new ResponseBuilder();
	private int				count	= 0;
	private int				total	= 0;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof HttpRequest) {
			HttpRequest request = (HttpRequest) msg;
			if (HttpHeaders.isContentLengthSet(request)) {
				total = (int) HttpHeaders.getContentLength(request);
				reader = new ByteBufToBytes(total);
			}
		}

		if (msg instanceof HttpContent) {
			HttpContent httpContent = (HttpContent) msg;
			ByteBuf content = httpContent.content();
			reader.reading(content);
			count += content.readableBytes();
			content.release();

			// 没有读完的情况下，是没有办法回写的，下面的代码不会有实际效果
			HttpResponse response = builder.buildResponse("data receving：" + count + "/" + total);
			ctx.writeAndFlush(response);

			if (reader.isEnd()) {
				response = builder.buildResponse("data receved：" + count + "/" + total);
				ctx.writeAndFlush(response);
			}
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

}
