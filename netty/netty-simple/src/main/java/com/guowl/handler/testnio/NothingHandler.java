package com.guowl.handler.testnio;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NothingHandler extends ChannelInboundHandlerAdapter {
	private static Logger	logger	= LoggerFactory.getLogger(NothingHandler.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(msg.toString());
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		logger.info("HttpServerInboundHandler.channelReadComplete");
		ctx.flush();
	}
}
