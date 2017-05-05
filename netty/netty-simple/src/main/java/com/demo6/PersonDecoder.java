package com.demo6;

import com.guowl.utils.ByteBufToBytes;
import com.guowl.utils.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PersonDecoder extends ByteToMessageDecoder {
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		byte n = "n".getBytes()[0];
		byte p = in.readByte();
		in.resetReaderIndex();
		if (n != p) {
			// 把读取的起始位置重置
			ByteBufToBytes reader = new ByteBufToBytes();
			out.add(ByteObjConverter.byteToObject(reader.read(in)));
		} else {
			// 执行其它的decode
			//ctx.fireChannelRead(in);
		}
	}
	
	public static void main(String[] args) {
		byte n = "n".getBytes()[0];
		byte b = 110;
		System.out.println(n ==b);
	}

}
