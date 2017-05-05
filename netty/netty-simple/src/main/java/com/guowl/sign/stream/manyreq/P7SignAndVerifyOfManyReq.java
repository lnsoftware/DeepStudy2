package com.guowl.sign.stream.manyreq;

import java.util.HashMap;
import java.util.Map;

import com.guowl.sign.http.ServerUrl;

// 发送多请求的方式
public class P7SignAndVerifyOfManyReq {
	public static void main(String[] args) throws Exception {
		p7SignAtach();
	}

	// p7 签 atach
	private static void p7SignAtach() {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("certid", "rsa");
			params.put("messageType", "http");
			params.put("businessType", "attachsign");
			params.put("digestalg", "SHA1");
			params.put("version", "1.0.1");

			HttpStreamClientOfManyReq client = new HttpStreamClientOfManyReq();
			client.connect(ServerUrl.URI);
		} catch (Exception e) {}
	}
}
