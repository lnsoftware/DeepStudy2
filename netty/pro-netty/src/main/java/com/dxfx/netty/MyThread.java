package com.dxfx.netty;

import io.netty.channel.Channel;

public class MyThread  extends Thread{
	
	private Channel channel;
	private Object msg;
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	@Override
	public void run() {
		channel.writeAndFlush(msg);
	}
	
	
	
	

}
