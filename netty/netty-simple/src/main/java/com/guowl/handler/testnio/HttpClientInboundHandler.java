package com.guowl.handler.testnio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;

import com.guowl.utils.ByteBufToBytes;

public class HttpClientInboundHandler extends ChannelInboundHandlerAdapter {
	private ByteBufToBytes reader;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof HttpResponse) {
			HttpResponse response = (HttpResponse) msg;
			if (HttpHeaders.isContentLengthSet(response)) {
				reader = new ByteBufToBytes((int) HttpHeaders.getContentLength(response));
			}
			for (String key : response.headers().names())  {
				System.out.println(key + ":" + response.headers().get(key));
			}
		}

		if (msg instanceof HttpContent) {
			HttpContent httpContent = (HttpContent) msg;
			ByteBuf content = httpContent.content();
			reader.reading(content);
			content.release();

			if (reader.isEnd()) {
				String resultStr = new String(reader.readFull());
				System.out.println("Server data length : " + resultStr.length());
			}
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.close();
	}
}
