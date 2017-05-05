package com.zhiyin.event.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfigUtil {
	public static Properties prop = new Properties();

	static {
		try {
			InputStream input = PropertiesConfigUtil.class
					.getResourceAsStream("/event_engine_config.properties");
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static String getConfig(String key) {
		return prop.getProperty(key);
	}
	
	public static void main(String[] args) {
		String url = getConfig("broker_url");
		System.out.println(url);
	}
}
