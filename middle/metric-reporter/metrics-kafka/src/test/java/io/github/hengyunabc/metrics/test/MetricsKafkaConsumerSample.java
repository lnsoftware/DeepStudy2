package io.github.hengyunabc.metrics.test;

import java.io.IOException;

import io.github.hengyunabc.metrics.KafkaConst;
import io.github.hengyunabc.metrics.MessageListener;
import io.github.hengyunabc.metrics.MetricsKafkaConsumer;

public class MetricsKafkaConsumerSample {

	String zookeeper;
	String topic;
	String group;

	MetricsKafkaConsumer consumer;

	public static void main(String[] args) throws IOException {

		String zookeeper = "10.5.144.164:2181,10.5.144.165:2181,10.5.144.166:2181";

		String topic = KafkaConst.TopicName;
		String group = "consumer-test";

		MetricsKafkaConsumer consumer = new MetricsKafkaConsumer();

		consumer = new MetricsKafkaConsumer();
		consumer.setZookeeper(zookeeper);
		consumer.setTopic(topic);
		consumer.setGroup(group);
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(String message) {
				System.err.println(message);
			}
		});
		consumer.init();

		System.in.read();

		consumer.desotry();
	}

}
