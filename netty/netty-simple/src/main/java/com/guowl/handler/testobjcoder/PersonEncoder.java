package com.guowl.handler.testobjcoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.guowl.utils.ByteObjConverter;

public class PersonEncoder extends MessageToByteEncoder<Person>  {

	@Override
	protected void encode(ChannelHandlerContext ctx, Person msg, ByteBuf out) throws Exception {
		out.writeBytes(ByteObjConverter.ObjectToByte(msg));
	}
}
