package com.zhiyin.event.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesAppender {
	private Properties prop = new Properties();
	
	public void append(String queueName, int maxConsumerCount) {
		try {
			FileInputStream in = new FileInputStream("activemq.properties");
			prop.load(in);
			
			String name = queueName.toUpperCase() + "_UME_EVENT";
			prop.setProperty("activemq.queue", name);
			if (maxConsumerCount > 2) {
				// 默认值为2，只可以往更大的数设置
				String max = String.valueOf(maxConsumerCount);
				prop.setProperty("activemq.maxConcurrentConsumers", max);
			}
			
			//save properties to project root folder
			FileOutputStream out = new FileOutputStream("activemq.properties");
    		prop.store(out, null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void append(String queueName) {
		append(queueName, 2);
	}
}
