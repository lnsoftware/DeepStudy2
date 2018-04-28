package asyn.write;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class NettyServer {
	
	public static void main(String[] args) {
		EventLoopGroup parentGroup = new NioEventLoopGroup(1);
		EventLoopGroup childGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(parentGroup, childGroup);
			serverBootstrap.channel(NioServerSocketChannel.class);
			serverBootstrap.option(ChannelOption.SO_BACKLOG, 128)
			                         .option(ChannelOption.SO_KEEPALIVE, true)
			                         .childHandler(new ChannelInitializer<SocketChannel>() {
										@Override
										protected void initChannel(SocketChannel ch)
												throws Exception {
											ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,Delimiters.lineDelimiter()[0]));
											ch.pipeline().addLast(new SimpleHandler());
											ch.pipeline().addLast(new StringEncoder());
										}
									});
			
			ChannelFuture future = serverBootstrap.bind(8080).sync();
			future.channel().closeFuture().sync();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
		}
		
		
		
		
	}

	public static class SimpleHandler extends ChannelInboundHandlerAdapter{

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg)
				throws Exception {
			System.out.println("开始读取数据============");
			if(msg instanceof ByteBuf){
				ByteBuf req = (ByteBuf)msg;
				String content = req.toString(Charset.defaultCharset());
				System.out.println(content);
				// 同步返回数据
				ctx.channel().writeAndFlush("李四\r\n");

				// 异步处理返回数据
				new Thread(){

					@Override
					public void run() {
						ctx.channel().writeAndFlush("this is in thread.\r\n");
					}
				}.start();
			}
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
				throws Exception {
			super.exceptionCaught(ctx, cause);
		}
	}

//	public static class SendThread

}
