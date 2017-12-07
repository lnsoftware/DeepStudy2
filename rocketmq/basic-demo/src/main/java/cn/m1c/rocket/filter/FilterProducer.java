package cn.m1c.rocket.filter;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 
 * @ClassName: FilterProducer 
 * @Description: 生产者
 * @author: Dell
 * @date: 2017-6-4 上午6:39:23
 */
public class FilterProducer {
	public static void main(String[] args) throws MQClientException {
		DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
		producer.setNamesrvAddr("192.168.1.244:9876;192.168.1.245:9876;192.168.1.242:9876;192.168.1.243:9876");
		producer.start();
		try {
			for (int i = 0; i < 100; i++) {
				Message msg = new Message("TopicFilter7",// topic
									"TagA",// tag
										("Hello MetaQ").getBytes());// body
				//这个属性作为过滤的条件
				msg.putUserProperty("SequenceId", String.valueOf(i));
				SendResult sendResult = producer.send(msg);
				System.out.println(sendResult+","+String.valueOf(i));
				Thread.sleep(100);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		producer.shutdown();
	}
}
