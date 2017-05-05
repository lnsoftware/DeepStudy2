package com.guowl.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufToBytes {
	private ByteBuf			temp;

	private boolean			end			= false;
	private int				bufLength	= 0;

	public ByteBufToBytes(int length) {
		temp = Unpooled.buffer(length);
		bufLength = length;
	}

	public ByteBufToBytes() {}

	public void init() {
		temp.release();
		temp = Unpooled.buffer(bufLength);
	}

	public void reading(ByteBuf datas) {
		if (!end) {
			int length = temp.writableBytes();
			if (temp.writableBytes() > datas.readableBytes()) {
				length = datas.readableBytes();
			}
			datas.readBytes(temp, length);
		}
		if (this.temp.writableBytes() != 0) {
			end = false;
		} else {
			end = true;
		}
	}

	public boolean isEnd() {
		return end;
	}

	public byte[] readFull() {
		try {
			byte[] contentByte = new byte[this.temp.readableBytes()];
			this.temp.readBytes(contentByte);
			return contentByte;
		} finally {
			this.temp.release();
		}
	}

	public byte[] read(ByteBuf datas) {
		byte[] bytes = new byte[datas.readableBytes()];
		datas.readBytes(bytes);
		return bytes;
	}

	public byte[] subBytes(byte[] src, int begin, int length) {
		byte[] bs = new byte[length];
		for (int i = begin; i < begin + length; i++)
			bs[i - begin] = src[i];
		return bs;
	}
}
