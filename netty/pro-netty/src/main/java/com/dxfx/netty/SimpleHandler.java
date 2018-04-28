package com.dxfx.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.nio.charset.Charset;

import com.alibaba.fastjson.JSONObject;
import com.dxfx.netty.param.RequestParam;
import com.dxfx.netty.param.Response;

public class SimpleHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		System.out.println("开始读取数据============");
		if(msg instanceof ByteBuf){
			ByteBuf req = (ByteBuf)msg;
			String content = req.toString(Charset.defaultCharset());
			System.out.println(content);
			
			RequestParam request = JSONObject.parseObject(content,RequestParam.class);
			Object result = Media.execute(request);
			
			Response res = new Response();
			res.setId(request.getId());
			res.setContent(result);
			ctx.channel().write(JSONObject.toJSONString(res));
			ctx.channel().writeAndFlush("\r\n");
		}
		
		
	}



	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx)
			throws Exception {
		super.channelWritabilityChanged(ctx);
	}



	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		
		if(evt instanceof IdleStateEvent){
			IdleStateEvent event = (IdleStateEvent)evt;
			if(event.equals(IdleState.READER_IDLE)){
				System.out.println("读空闲====");
				ctx.close();
			}else if(event.equals(IdleState.WRITER_IDLE)){
				System.out.println("写空闲====");
			}else if(event.equals(IdleState.WRITER_IDLE)){
				System.out.println("读写空闲====");
				ctx.channel().writeAndFlush("ping\r\n");
			}
			
		}
		
		super.userEventTriggered(ctx, evt);
	}

	
	


	
	
	
	
	
	
	

}
