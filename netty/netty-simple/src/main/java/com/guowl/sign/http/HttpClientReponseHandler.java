package com.guowl.sign.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;

import com.guowl.utils.ByteBufToBytes;

public class HttpClientReponseHandler extends ChannelInboundHandlerAdapter {
	private ByteBufToBytes	reader;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof HttpResponse) {
			HttpResponse response = (HttpResponse) msg;
			if (HttpHeaders.isContentLengthSet(response)) {
				reader = new ByteBufToBytes((int) HttpHeaders.getContentLength(response));
			}

			HttpHeaders heads = response.headers();
			for (String name : heads.names()) {
				System.out.println(name + " : " + heads.get(name));
			}
			System.out.println();
		}

		if (msg instanceof HttpContent) {
			HttpContent httpContent = (HttpContent) msg;
			ByteBuf content = httpContent.content();
			reader.reading(content);
			content.release();
			if (reader.isEnd()) {
				System.out.println("HttpContent length :  ");
				System.out.println(reader.readFull().length);
			}
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("end time : " + System.currentTimeMillis() / 1000);
		ctx.close();
	}
}
