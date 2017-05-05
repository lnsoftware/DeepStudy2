package com.guowl.utils;

public class BytesUtils {
	public static byte[] getBytes(int length) {
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
			result[i] = 1;
		}
		return result;
	}
	
	public static byte[] getStrBytes(int length){
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < length; i++) {
			result.append("s");
		}
		return result.toString().getBytes();
	}
}
