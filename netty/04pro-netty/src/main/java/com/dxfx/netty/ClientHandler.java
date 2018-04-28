package com.dxfx.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

public class ClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ctx.channel().attr(AttributeKey.valueOf("ChannelKey")).set(msg.toString());
		ctx.channel().close();
	}
	
	

}
