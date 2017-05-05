package com.guowl.websocket.stream.client;

import java.net.URI;

public class StreamClient {
	private StreamClient() {}

	public static void main(String... args) throws Exception {
		URI uri;
		if (args.length > 0) {
			uri = new URI(args[0]);
		} else {
			uri = new URI("ws://172.16.7.60:8080/websocket");
		}

		new StreamClientRunner(uri).run();
	}
}
