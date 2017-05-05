package com.guowl.handler.testmultihandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// ���տͻ�����Ϣ����ӡ
public class InboundHandler1 extends ChannelInboundHandlerAdapter {
	private static Logger	logger	= LoggerFactory.getLogger(InboundHandler1.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("InboundHandler1.channelRead: ctx :" + ctx);
		ctx.fireChannelRead(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		logger.info("InboundHandler1.channelReadComplete");
		ctx.flush();
	}
}
