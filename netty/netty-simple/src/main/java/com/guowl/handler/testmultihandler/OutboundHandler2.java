package com.guowl.handler.testmultihandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// ���տͻ�����Ϣ����ӡ
public class OutboundHandler2 extends ChannelOutboundHandlerAdapter {
	private static Logger	logger	= LoggerFactory.getLogger(OutboundHandler2.class);
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		logger.info("OutboundHandler2.write");
		super.write(ctx, msg, promise);
	}
}
