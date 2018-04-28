package com.dxfx.netty.https;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.nio.charset.Charset;

import com.alibaba.fastjson.JSONObject;
import com.dxfx.netty.Media;
import com.dxfx.netty.param.RequestParam;

public class HttpsServerHandler extends ChannelInboundHandlerAdapter {

	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		try {
			if(msg instanceof FullHttpRequest){
				FullHttpRequest req = (FullHttpRequest)msg;
				//获取请求内容
				String content = req.content().toString(Charset.defaultCharset());
				RequestParam request = JSONObject.parseObject(content,RequestParam.class);
				Object result = Media.execute(request);
				
				DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
						                                                    HttpResponseStatus.OK, Unpooled.wrappedBuffer(JSONObject.toJSONString(result).getBytes(Charset.defaultCharset())));
				response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
				response.headers().set(HttpHeaderNames.CONTENT_LENGTH,response.content().readableBytes());
				response.headers().set(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
				ctx.channel().writeAndFlush(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ctx.channel().writeAndFlush("");
		}
		
		
	}

	
	
	
}
