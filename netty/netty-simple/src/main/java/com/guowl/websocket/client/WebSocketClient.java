package com.guowl.websocket.client;

import java.net.URI;

public class WebSocketClient {
	private WebSocketClient() {}

	public static void main(String... args) throws Exception {
		URI uri;
		if (args.length > 0) {
			uri = new URI(args[0]);
		} else {
			uri = new URI("ws://localhost:8080/websocket");
		}

		new WebSocketClientRunner(uri).run();
	}
}
