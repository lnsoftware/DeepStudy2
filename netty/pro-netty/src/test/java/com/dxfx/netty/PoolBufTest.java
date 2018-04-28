package com.dxfx.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

import org.junit.Test;

public class PoolBufTest {
	
	@Test
	public void testPool(){
		
//		ByteBuf  data = Unpooled.buffer();
//		data.writeBytes("helloworld".getBytes());
		ByteBuf  buf = PooledByteBufAllocator.DEFAULT.buffer(1024);
		buf.writeBytes("helloworld".getBytes());
		buf.release();
	}

}
