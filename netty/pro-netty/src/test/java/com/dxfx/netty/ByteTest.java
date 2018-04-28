package com.dxfx.netty;

import java.nio.charset.Charset;

import org.junit.Test;

public class ByteTest {
	
	@Test
	public void testSize(){
		String s = "this is test Response \r\n";
		System.out.println(s.getBytes(Charset.defaultCharset()).length);
	}
	
	@Test
	public void test01(){
		  int shift = 2048 ^ 1 << 11;
		  System.out.println(shift);
	}
	
	@Test
	public void test02(){
		long i = 1;
		for(int j=0;j<11;j++){
			i=i*2;
		}
		System.out.println(i);
	}

	@Test
	public void test03(){
		int i = (1 << 24 -10);
		System.out.println(i);
	}
	
	@Test
	public void test04(){
		System.out.println(0x3FFFFFFF);
		//1073741823
		//1073741824
		System.out.println(0x4000000000000000L>>32);
		System.out.println(1073741823 & 1073741824);
		long bitmapIdx=25l;
		System.out.println((0x4000000000000000L | (long) bitmapIdx << 32)>>32 & 0x3FFFFFFF);
	}
	
	@Test
	public void test05(){
		
	    int normalizedCapacity = 513;
        normalizedCapacity --;
        normalizedCapacity |= normalizedCapacity >>>  1;
        normalizedCapacity |= normalizedCapacity >>>  2;
        normalizedCapacity |= normalizedCapacity >>>  4;
        normalizedCapacity |= normalizedCapacity >>>  8;
        normalizedCapacity |= normalizedCapacity >>> 16;
        normalizedCapacity ++;

        if (normalizedCapacity < 0) {
            normalizedCapacity >>>= 1;
        }

      System.out.println(normalizedCapacity);
	}

}
