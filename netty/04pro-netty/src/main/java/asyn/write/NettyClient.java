package asyn.write;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;

public class NettyClient {
	public static EventLoopGroup group=null;
	public static Bootstrap boostrap=null;
	static{
		 group = new NioEventLoopGroup();
		 boostrap = new Bootstrap();
		boostrap.channel(NioSocketChannel.class);
		boostrap.group(group);
		boostrap.option(ChannelOption.SO_KEEPALIVE, true)
		             .handler(new ChannelInitializer<NioSocketChannel>() {
						@Override
						protected void initChannel(NioSocketChannel ch)
								throws Exception {
							ch.pipeline().addLast(new DelimiterBasedFrameDecoder
									(Integer.MAX_VALUE,Delimiters.lineDelimiter()[0]));
							ch.pipeline().addLast(new StringDecoder());
							ch.pipeline().addLast(new NettyClient.ClientHandler());
							ch.pipeline().addLast(new StringEncoder());
							
							
						}
					});
	}
	public static void main(String[] args) {
		
		try{
			ChannelFuture future = boostrap.connect("localhost", 8080).sync();
			String person = "张三\r\n";
			
			future.channel().writeAndFlush(person);

			future.channel().closeFuture().sync();
			
			Object result = future.channel().attr(AttributeKey.valueOf("ChannelKey")).get();
			System.out.println(result);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}

	}

	public static class ClientHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg)
				throws Exception {
			ctx.channel().attr(AttributeKey.valueOf("ChannelKey")).set(msg.toString());
			System.out.println("客户端返回数据："+msg.toString());
//			ctx.channel().close();
		}
	}

}