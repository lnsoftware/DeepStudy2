package com.guowl.handler.testmultihandler;

import java.net.SocketAddress;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// ���տͻ�����Ϣ����ӡ
public class OutboundHandler1 extends ChannelOutboundHandlerAdapter {
	private static Logger	logger	= LoggerFactory.getLogger(OutboundHandler1.class);

	@Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) {
		logger.info("OutboundHandler1.close");
        System.out.println("Closing ..");
    }
	
	@Override
	public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
		logger.info("OutboundHandler1.bind");
		super.bind(ctx, localAddress, promise);
	}
	
	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress,
			ChannelPromise promise) throws Exception {
		logger.info("OutboundHandler1.connect");
		super.connect(ctx, remoteAddress, localAddress, promise);
	}
	
	@Override
	public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		logger.info("OutboundHandler1.deregister");
		super.deregister(ctx, promise);
	}
	
	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		logger.info("OutboundHandler1.disconnect");
		super.disconnect(ctx, promise);
	}
	@Override
	public void flush(ChannelHandlerContext ctx) throws Exception {
		logger.info("OutboundHandler1.flush");
		super.flush(ctx);
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		logger.info("OutboundHandler1.handlerAdded");
		super.handlerAdded(ctx);
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		logger.info("OutboundHandler1.handlerRemoved");
		ctx.fireChannelActive();
	}
	
	@Override
	public void read(ChannelHandlerContext ctx) throws Exception {
		logger.info("OutboundHandler1.read");
		super.read(ctx);
	}
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		logger.info("OutboundHandler1.write");
		String response = "I am ok!";
		ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
		encoded.writeBytes(response.getBytes());
		ctx.write(encoded);
		ctx.flush();
	}
}

