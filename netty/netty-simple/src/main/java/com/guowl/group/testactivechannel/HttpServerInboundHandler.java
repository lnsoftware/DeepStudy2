package com.guowl.group.testactivechannel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {
	private static Logger	logger	= LoggerFactory.getLogger(HttpServerInboundHandler.class);

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		// 记录每一个活跃的channel
		HttpServer.allChannels.add(ctx.channel());
		logger.info("\n\n begin to print channel info :");
		logger.info("HttpServer.allChannels.size:" + HttpServer.allChannels.size());
		if (HttpServer.allChannels.size() > 10) {
			for (Channel c : HttpServer.allChannels) {
				logger.info(c.toString());
			}

			// 关闭
			HttpServer.allChannels.close().awaitUninterruptibly();
		}
	}
}
