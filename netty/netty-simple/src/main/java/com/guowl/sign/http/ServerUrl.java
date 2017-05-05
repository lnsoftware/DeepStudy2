package com.guowl.sign.http;

import java.net.URI;
import java.net.URISyntaxException;

public class ServerUrl {
	public static URI	URI	= null;
	static {
		try {
//			URI = new URI("http://192.168.9.61:9000");
			URI = new URI("http://172.16.7.60:8000");
		} catch (URISyntaxException e) {}
	}
}
