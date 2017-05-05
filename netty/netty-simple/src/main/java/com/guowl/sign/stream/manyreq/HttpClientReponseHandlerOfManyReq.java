package com.guowl.sign.stream.manyreq;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpVersion;

import com.guowl.sign.http.ServerUrl;
import com.guowl.utils.ByteBufToBytes;
import com.guowl.utils.BytesUtils;

public class HttpClientReponseHandlerOfManyReq extends ChannelInboundHandlerAdapter {
	private ByteBufToBytes	reader;
	private int				length		= 1024 * 1024;
	private URI				uri			= ServerUrl.URI;
	private int				totalLength	= 1024 * 1024 * 10;
	private byte[]			datas		= new byte[0];
	private int				index		= 0;
	private int count = 0;

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

				if (index < totalLength) {
					int to = index + length;
					if (index + length > totalLength) {
						to = totalLength;
					}
					DefaultFullHttpRequest request = buildReq(Arrays.copyOfRange(datas, index, to));
					index = to;
					ctx.writeAndFlush(request);
				}
			}
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("end time : " + System.currentTimeMillis() / 1000);
//		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.datas = BytesUtils.getBytes(totalLength);
		DefaultFullHttpRequest request = buildReq(Arrays.copyOf(datas, length));
		index += length;
		ctx.writeAndFlush(request);
	}

	private DefaultFullHttpRequest buildReq(byte[] data) {
		System.out.println((++count) + " request content length : " + data.length);
		if (data.length<1200) {
			System.out.println("last request");
		}
		DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST,
				uri.toASCIIString(), Unpooled.wrappedBuffer(data));

		request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
		Map<String, String> params = new HashMap<String, String>();
		params.put("certid", "rsa");
		params.put("messageType", "http");
		params.put("businessType", "attachsign");
		params.put("digestalg", "SHA1");
		params.put("version", "1.0.1");
		params.put("content_size", totalLength + "");
		for (String key : params.keySet()) {
			request.headers().set(key, params.get(key));
		}
		return request;
	}
}
