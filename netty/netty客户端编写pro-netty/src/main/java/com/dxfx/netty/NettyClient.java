package com.dxfx.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {
	
	public static void main(String[] args) {
		
		EventLoopGroup group = new NioEventLoopGroup(1);
		
		try{
			Bootstrap boostrap = new Bootstrap();
			boostrap.channel(NioSocketChannel.class);
			boostrap.group(group);
			boostrap.option(ChannelOption.SO_KEEPALIVE, true)
			             .handler(new ChannelInitializer<NioSocketChannel>() {
							@Override
							protected void initChannel(NioSocketChannel ch)
									throws Exception {
								ch.pipeline().addLast(new DelimiterBasedFrameDecoder
										(Integer.MAX_VALUE,Delimiters.lineDelimiter()[0]));
								ch.pipeline().addLast(new ClientHandler());
								ch.pipeline().addLast(new StringEncoder());
								
							}
						});
			
			ChannelFuture future = boostrap.connect("localhost", 8080).sync();
			String person = "张三\r\n";
			
//			ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer();
//			buf.writeBytes(person.getBytes(Charset.defaultCharset()));
			future.channel().writeAndFlush(person);
//			future.channel().writeAndFlush(Delimiters.lineDelimiter()[0]);
			
			
			future.channel().closeFuture().sync();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}
		
		
		
	}

}
